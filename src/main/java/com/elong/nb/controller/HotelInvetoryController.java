package com.elong.nb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elong.nb.common.gson.GsonUtil;
import com.elong.nb.common.model.ErrorCode;
import com.elong.nb.common.model.RestRequest;
import com.elong.nb.common.model.RestResponse;
import com.elong.nb.common.util.ValidateUtil;
import com.elong.nb.model.inventory.InventoryCondition;
import com.elong.nb.model.inventory.InventoryResult;
import com.elong.nb.model.inventory.ValidateInventoryCondition;
import com.elong.nb.model.inventory.ValidateInventoryResult;
import com.elong.nb.service.IInventoryService;
import com.elong.nb.service.IValidateInventoryService;

@Controller
public class HotelInvetoryController {
	@Resource
	private IInventoryService inventoryService;
	@Resource
	private IValidateInventoryService validateInventoryService;

	@RequestMapping(value = "/api/Hotel/GetInventories", method = RequestMethod.POST)
	public ResponseEntity<byte[]> getInventories(HttpServletRequest request)
			throws Exception {
		RestRequest<InventoryCondition> restRequest = GsonUtil.toReq(request,
				InventoryCondition.class, null);
		String rst = validateInventoryRequest(restRequest);
		if (StringUtils.isNotBlank(rst)) {
			RestResponse<InventoryResult> response = new RestResponse<InventoryResult>(
					restRequest.getGuid());
			response.setCode(rst);
			response.setResult(null);
			return new ResponseEntity<byte[]>(GsonUtil.toJson(
					response,
					restRequest.getVersion() == null ? 0d : restRequest
							.getVersion()).getBytes(), HttpStatus.OK);
		}
		RestResponse<InventoryResult> response = this.inventoryService.getInventories(restRequest);
		return new ResponseEntity<byte[]>(GsonUtil.toJson(response,
				restRequest.getVersion()).getBytes(), HttpStatus.OK);

	}

	@RequestMapping(value = "/api/Hotel/ValidateInventory", method = RequestMethod.POST)
	public ResponseEntity<byte[]> validateInventory(HttpServletRequest request)
			throws Exception {
		RestRequest<ValidateInventoryCondition> restRequest = GsonUtil.toReq(
				request, ValidateInventoryCondition.class, null);
		String rst = validateValidateInventoryRequest(restRequest);// validateInventoryRequest(restRequest);
		if (StringUtils.isNotBlank(rst)) {
			RestResponse<InventoryResult> response = new RestResponse<InventoryResult>(
					restRequest.getGuid());
			response.setCode(rst);
			response.setResult(null);
			return new ResponseEntity<byte[]>(GsonUtil.toJson(
					response,
					restRequest.getVersion() == null ? 0d : restRequest
							.getVersion()).getBytes(), HttpStatus.OK);
		}
		RestResponse<ValidateInventoryResult> response = this.validateInventoryService
				.getValidateInventories(restRequest);
		return new ResponseEntity<byte[]>(GsonUtil.toJson(response,
				restRequest.getVersion()).getBytes(), HttpStatus.OK);

	}
	/**
	 * 校验库存入参合法性
	 * @param restRequest
	 * @return
	 */
	private String validateInventoryRequest(RestRequest<InventoryCondition> restRequest) {
		StringBuffer sb = new StringBuffer(ValidateUtil.validateRestRequest(restRequest));
		if(StringUtils.isBlank(restRequest.getRequest().getHotelIds())||restRequest.getRequest().getHotelIds().split(",").length>10){
			sb.append(ErrorCode.Common_NumberIdsFormatErrorAndLessThanTen);
			return sb.toString();
		}
		if (restRequest.getRequest().getStartDate() == null) {
			sb.append(ErrorCode.Common_StartDateRequired);
			return sb.toString();
		}
		if (restRequest.getRequest().getEndDate() == null) {
			sb.append(ErrorCode.Common_EndDateRequired);
			return sb.toString();
		}
		if (restRequest.getRequest().getStartDate().getTime()>restRequest.getRequest().getEndDate().getTime()) {
			sb.append(ErrorCode.Common_StartDateLessThanEndDate);
			return sb.toString();
		}
//		if (StringUtils.isBlank(restRequest.getRequest().getHotelCodes())&& StringUtils.isBlank(restRequest.getRequest().getHotelIds())) {
//			sb.append(ErrorCode.Common_NumberIdsFormatErrorAndLessThanTen);
//			return sb.toString();
//		}
		if(!StringUtils.isBlank(restRequest.getRequest().getHotelCodes())){
			if(restRequest.getRequest().getHotelIds().contains(",")){
				sb.append(ErrorCode.Common_HotelIdRequiredOnlyOne);
				return sb.toString();
			}else if(restRequest.getRequest().getHotelCodes().split(",").length>10){
				sb.append(ErrorCode.Common_NumberCodesFormatErrorAndLessThanTen);
				return sb.toString();
			}
		}
		return sb.toString();
	}
	/**
	 * 校验库存验证入参合法性
	 * @param restRequest
	 * @return
	 */
	private String validateValidateInventoryRequest(RestRequest<ValidateInventoryCondition> restRequest) {
		StringBuffer sb = new StringBuffer(ValidateUtil.validateRestRequest(restRequest));
		if (StringUtils.isBlank(restRequest.getRequest().getHotelId())) {
			sb.append(ErrorCode.Common_HotelIdRequired);
			return sb.toString();
		}
		if (StringUtils.isBlank(restRequest.getRequest().getHotelCode())) {
			sb.append(ErrorCode.Common_HotelCodeRequired);
			return sb.toString();
		}
		if (StringUtils.isBlank(restRequest.getRequest().getRoomTypeId())) {
			sb.append(ErrorCode.Common_RoomTypeIdRequired);
			return sb.toString();
		}
		if (restRequest.getRequest().getArrivalDate() == null) {
			sb.append(ErrorCode.Common_ArrivalDateRequired);
			return sb.toString();
		}
		if (restRequest.getRequest().getDepartureDate() == null) {
			sb.append(ErrorCode.Common_DepartureDateRequired);
			return sb.toString();
		}
		if (restRequest.getRequest().getArrivalDate().getTime()>restRequest.getRequest().getDepartureDate().getTime()) {
			sb.append(ErrorCode.Common_StartDateLessThanEndDate);
			return sb.toString();
		}
		return sb.toString();
	}
}