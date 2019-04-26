package com.cm.service.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Order;
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
		if (obj instanceof Party) {
			item = new RecentItem();
			Party p = (Party) obj;
			item.setChangeType(changeType);
			item.setItemType("PARTY");
			item.setChangeDescription(action + " party " + p.getPartyName() + " address " + p.getAddress());
			item.setChangeTime(sdf.format(new Date()));
			item.setItemName(p.getPartyName());
			item.setItemId(p.getPartyId());
			item = repository.save(item);
		} else if (obj instanceof Stock) {
			item = new RecentItem();
			Stock s = (Stock) obj;
			item.setChangeType(changeType);
			item.setItemType("STOCK");
			item.setChangeDescription(
					action + " stock " + s.getStockDescription() + " of quantity " + s.getAvailableStocks());
			item.setChangeTime(sdf.format(new Date()));
			item.setItemName(s.getStockDescription());
			item.setItemId(s.getStockId());
			item = repository.save(item);
		} else if (obj instanceof Order) {
			item = new RecentItem();
			Order order = (Order) obj;
			item.setChangeType(changeType);
			item.setItemType("ORDER");
			Party party = order.getParty();
			String act = changeType.equals("IN") ? "Placed" : (changeType.equals("UP") ? "Updated" : "Deleted");
			item.setChangeDescription(act + " order for " + party.getPartyName() + ", " + party.getAddress()
					+ " of amount " + order.getTotalOrderAmount());

			item.setChangeTime(sdf.format(new Date()));
			item.setItemName(changeType.equals("IN") ? "New Order - " + party.getPartyName() : "Updating Order");
			item.setItemId(order.getOrderId());
			item = repository.save(item);
		}
		return item;
	}

	public List<RecentItem> getRecentItems() {
		List<RecentItem> recentChanges = repository.findAll();
		
			if (recentChanges != null) {
				Comparator<RecentItem> itemComparator = (i1, i2) -> {
					try {
						Date d1 = sdf.parse(i1.getChangeTime());
						Date d2 = sdf.parse(i2.getChangeTime());
						return (d1.compareTo(d2)) * -1; // reversing the order
					} catch (Exception e) {
						e.printStackTrace();
						return 0;
					}
				};
				
				Collections.sort(recentChanges, itemComparator);
			}
		
		return recentChanges;
	}

	private String getAction(String changeType) {
		String action = "";
		if (changeType.equals("IN")) {
			action = "Inserted";
		} else if (changeType.equals("UP")) {
			action = "Updated";
		} else if (changeType.equals("DL")) {
			action = "Deleted";
		}
		return action;
	}
}
