package graphene.dao.sql;

import graphene.model.sql.FINFLOWDaily120;
import graphene.model.sql.QFINFLOWDaily120;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLServer2005Templates;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.EntityPath;

public class FinFlowDaily100DAOSQLImpl {

	private static final Logger logger = LoggerFactory.getLogger(FinFlowDaily100DAOSQLImpl.class);
	@Inject
	private BoneCP cp;

	protected SQLQuery from(final Connection conn, final EntityPath<?>... o) throws SQLException {
		final SQLTemplates dialect = new SQLServer2005Templates(); // SQL-dialect
		return new SQLQuery(conn, dialect).from(o);
	}

	protected Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return cp.getConnection();
	}

	public List<FINFLOWDaily120> getFlowsBetween(final String... entities) {
		final QFINFLOWDaily120 q = new QFINFLOWDaily120("q");
		List<FINFLOWDaily120> list = new ArrayList<FINFLOWDaily120>();
		try {
			list = from(getConnection(), q).where(q.fromEntityId.in(entities).and(q.toEntityId.in(entities))).list(q);
		} catch (final SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public List<FINFLOWDaily120> getFlowsBoth(final String entity) {
		final QFINFLOWDaily120 q = new QFINFLOWDaily120("q");
		List<FINFLOWDaily120> list = new ArrayList<FINFLOWDaily120>();
		try {
			list = from(getConnection(), q).where(q.fromEntityId.eq(entity).or(q.toEntityId.eq(entity))).list(q);
		} catch (final SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public List<FINFLOWDaily120> getFlowsFrom(final String entity) {
		final QFINFLOWDaily120 q = new QFINFLOWDaily120("q");
		List<FINFLOWDaily120> list = new ArrayList<FINFLOWDaily120>();
		try {
			list = from(getConnection(), q).where(q.fromEntityId.eq(entity)).list(q);
		} catch (final SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public List<FINFLOWDaily120> getFlowsFromTo(final String from, final String to) {
		final QFINFLOWDaily120 q = new QFINFLOWDaily120("q");
		List<FINFLOWDaily120> list = new ArrayList<FINFLOWDaily120>();
		try {
			list = from(getConnection(), q).where(q.fromEntityId.eq(from).and(q.toEntityId.eq(to))).list(q);
		} catch (final SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public List<FINFLOWDaily120> getFlowsTo(final String entity) {
		final QFINFLOWDaily120 q = new QFINFLOWDaily120("q");
		List<FINFLOWDaily120> list = new ArrayList<FINFLOWDaily120>();
		try {
			list = from(getConnection(), q).where(q.toEntityId.eq(entity)).list(q);
		} catch (final SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
