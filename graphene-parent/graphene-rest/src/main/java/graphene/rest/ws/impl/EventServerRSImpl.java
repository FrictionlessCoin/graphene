package graphene.rest.ws.impl;

import graphene.dao.EventServer;
import graphene.model.query.EventQuery;
import graphene.model.view.events.DirectedEventRow;
import graphene.model.view.events.DirectedEvents;
import graphene.model.view.events.EventStatistics;
import graphene.rest.ws.EventServerRS;
import graphene.util.FastNumberUtils;
import graphene.util.stats.TimeReporter;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class EventServerRSImpl implements EventServerRS {

	@Inject
	private Logger logger;

	// XXX: This is not the way to do this.
	@Persist
	private List<DirectedEventRow> prevAllRows;
	// XXX: This is not the way to do this.
	@Persist
	private EventQuery prevQuery;
	// XXX: This is not the way to do this.
	@Persist
	private EventStatistics prevStatistics;

	@Inject
	private EventServer eventServer;

	@Override
	public DirectedEvents getEvents(String[] account, int start, int limit,
			String minAmount, String maxAmount, String minSecs, String maxSecs,
			String comments, String sortColumn) {
		TimeReporter t = new TimeReporter("getEvents", logger);
		if (start < 0) {
			logger.debug("Got Events request with invalid starting offset "
					+ start);
			return new DirectedEvents(); // bug in extjs often asks for
											// negative start
		}

		EventQuery q = new EventQuery();
		q.addIds(Arrays.asList(account));
		q.setFirstResult(start);
		q.setMaxResult(limit);
		q.setMinSecs(FastNumberUtils.parseLongWithCheck(minSecs, 0));
		q.setMaxSecs(FastNumberUtils.parseLongWithCheck(maxSecs, 0));
		q.setMinAmount(Double.parseDouble(minAmount.isEmpty() ? "0" : minAmount));
		q.setMaxAmount(Double.parseDouble(maxAmount.isEmpty() ? "0" : maxAmount));
		q.setComments(comments);
		q.setSortAndDirection(sortColumn);

		DirectedEvents e = eventServer.getEvents(q);
		t.logAsCompleted();
		return e;
	}

	public EventStatistics getPairMonthlyStatistics(
			@QueryParam("accountNumber") String account) {
		if (!prevQuery.isSingleId()) {
			return new EventStatistics(); // to return an empty structure
		}
		return prevStatistics; // generated when previous query was executed
	}

	public EventStatistics getPairDailyStatistics(
			@QueryParam("accountNumber") String account,
			@QueryParam("year") int year, @QueryParam("month") int month)

	{
		if (!prevQuery.isSingleId()) {
			return new EventStatistics(); // to return an empty structure
		}
		if (!prevStatistics.account.equals(account)) {
			logger.debug("Account does not match the last one loaded");
			return new EventStatistics(); // to return an empty structure
		}

		EventStatistics stats = new EventStatistics();

		return stats;
	}

}
