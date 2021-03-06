/**
 * 
 */
package graphene.services;

import graphene.business.commons.exception.DataAccessException;
import graphene.dao.RoleDAO;
import graphene.model.idl.G_Role;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.joda.time.DateTime;
import org.slf4j.Logger;

/**
 * @author djue
 * 
 */
public class SimpleRoleDAOImpl implements RoleDAO {

	private static final G_Role ADMIN = new G_Role("admin", "admin", "Administrator Role", DateTime.now().getMillis());
	private static final G_Role USER = new G_Role("user", "user", "User Role", DateTime.now().getMillis());

	@Inject
	private Logger logger;

	public SimpleRoleDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public G_Role create(final G_Role g_Role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(final String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<G_Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public G_Role getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphene.dao.RoleDAO#getForUsername(java.lang.String)
	 */
	public List<G_Role> getForUser(final String id) {
		final ArrayList<G_Role> list = new ArrayList<G_Role>();
		list.add(ADMIN);
		list.add(USER);
		return list;
	}

	@Override
	public G_Role getRoleByRolename(final String groupname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public G_Role save(final G_Role g) {
		// TODO Auto-generated method stub
		return null;
	}

}
