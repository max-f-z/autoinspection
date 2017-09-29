package com.autoinspection.polaris.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.AppointmentLimitEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.model.mapper.AppointmentLimitMapper;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.AppointmentService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.appointment.AppointmentDetail;
import com.autoinspection.polaris.vo.appointment.AppointmentsReq;
import com.autoinspection.polaris.vo.appointment.AppointmentsResp;
import com.autoinspection.polaris.vo.appointment.SetAppointmentLimitReq;
import com.autoinspection.polaris.vo.wx.AppointmentRequest;

@RestController
@RequestMapping(value="${api.path}/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentLimitMapper alMapper;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(path = "/setLimit", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public Result<String> setAppointmentLimit(@RequestBody SetAppointmentLimitReq req, @CurrentUser UserVo user) throws BizException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date requested = df.parse(req.getDate());
			
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(new Date());
			cal2.setTime(requested);
			boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
			                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
			if (sameDay) {
				throw new BizException(ErrorCode.INVALID_PARAM);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		AppointmentLimitEntity en = alMapper.check(req.getDate(), req.getStationId());
		if (en != null) {
			en.setAppointmentDate(req.getDate());
			en.setLimit(req.getLimit());
			en.setStationId(req.getStationId());
			en.setOperatorId(user.getUid());
			alMapper.updateLimit(en);
		} else {
			en = new AppointmentLimitEntity();
			en.setAppointmentDate(req.getDate());
			en.setLimit(req.getLimit());
			en.setStationId(req.getStationId());
			en.setOperatorId(user.getUid());
			alMapper.insertLimit(en);
		}
		
		return new Result<>("");
	}
	
	@RequestMapping(path = "/appointments", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AppointmentsResp getAppointments(@RequestBody AppointmentsReq req) throws BizException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date start = new Date();
		Date end = new Date();
		try {
			start = df.parse(req.getStartDate());
			end = df.parse(req.getEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		AppointmentsResp resp = new AppointmentsResp();
		List<AppointmentDetail> details = new ArrayList<AppointmentDetail>();
		
		for (;;) {
			AppointmentRequest r = new AppointmentRequest();
			r.setDate(df.format(start));
			r.setStationId(req.getStationId());
			
			AppointmentDetail d = new AppointmentDetail();
			
			List<RemainEntity> re = appointmentService.listAppointments(r);
			int limit = alMapper.getLimitByDate(r.getDate(), r.getStationId());
			for (RemainEntity rre : re) {
				rre.setLimit(limit);
			}
			d.setDate(r.getDate());
			d.setSlots(re);
			details.add(d);
			
			if (df.format(start).equals(df.format(end))) {
				break;
			}
			Calendar cal = Calendar.getInstance();
	        cal.setTime(start);
	        cal.add(Calendar.DATE, 1); 
	        start = cal.getTime();
		}
		resp.setItems(details);
		return resp;
	}
}
