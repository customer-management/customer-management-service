package com.cm.service.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Party;
import com.cm.service.models.hib.RecentItem;
import com.cm.service.models.hib.Stock;
import com.cm.service.repository.ChangeLogRepository;

@Component
public class ChangeLogService {
	private static final String DATE_FORMAT = "dd MMM yyyy 'at' HH:mm.ss";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	@Autowired
	private ChangeLogRepository repository;
	
	public RecentItem insertChangeLog(String objectType, Object obj, String changeType) {
		String action = getAction(changeType);
		RecentItem item = null;
		if(obj instanceof Party) {
			item = new RecentItem();
			Party p = (Party) obj;
			item.setChangeType(changeType);
			item.setItemType("PARTY");
			item.setChangeDescription(action + " party " + p.getPartyName() + " address " + p.getAddress());
			item.setChangeTime(sdf.format(new Date()));
			item.setItemName(p.getPartyName());
			item.setItemId(p.getPartyId());
			item = repository.save(item);
		} else if(obj instanceof Stock) {
			item = new RecentItem();
			Stock s = (Stock) obj;
			item.setChangeType(changeType);
			item.setItemType("STOCK");
			item.setChangeDescription(action + " stock " + s.getStockDescription() + " of quantity " + s.getAvailableStocks());
			item.setChangeTime(sdf.format(new Date()));
			item.setItemName(s.getStockDescription());
			item.setItemId(s.getStockId());
			item = repository.save(item);
		}
		return item;
	}
	public List<RecentItem> getRecentItems() {
		return repository.findAll();
	}
	private String getAction(String changeType) {
		String action = "";
		if(changeType.equals("IN")) {
			action = "Inserted";
		} else if(changeType.equals("UP")) {
			action = "Updated";
		} else if(changeType.equals("DL")) {
			action = "Deleted";
		}
		return action;
	}
}
