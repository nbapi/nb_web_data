package com.elong.nb.bookingdata.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import com.elong.nb.common.model.RestRequest;
import com.elong.nb.model.bookingdata.BookingDataCondition;
import com.elong.nb.model.rate.bean.Rate;
import com.elong.nb.service.impl.RateService;

public class RateThread implements Callable<List<Rate>> {
	
	RestRequest<BookingDataCondition> request;
	RateService rateService;
	
	public RateThread(RestRequest<BookingDataCondition> request,RateService rateService)
	{
		this.request =request;
		this.rateService= rateService;
	}

	@Override
	public List<Rate> call() throws Exception {
		
		List<Rate> obj =null;
		try
        {
        	Calendar cd = Calendar.getInstance();
        	cd.setTime(request.getRequest().getDepartureDate()); 
        	cd.add(Calendar.DATE, -1);
            List<Rate> list = rateService.getRate(request.getProxyInfo(), request.getRequest().getHotelId(), request.getRequest().getHotelCode(), 
            		request.getRequest().getArrivalDate(), 
            		cd.getTime(),//request.getRequest().getDepartureDate().AddDays(-1), 
            		request.getRequest().getPaymentType(), request.getProxyInfo().getLowestProfitPercent(),request.getGuid());
            //List<Rate> rates = list.Where(x => x.RoomTypeId == request.Request.RoomTypeId && x.RateplanId == request.Request.RatePlanId).ToList();
            List<Rate> rates = new ArrayList<Rate>();
            if(list !=null)
            {
            for(Rate x: list)
            {
            	if(request.getRequest().getRoomTypeId().equals(x.getRoomTypeId()) && request.getRequest().getRatePlanId()==x.getRateplanId())
            		rates.add(x);
            }
            
            }
            
            obj = rates;
        }
        catch(Exception e)
        {
        	return null;
        }
        return obj;
		
	}
}
