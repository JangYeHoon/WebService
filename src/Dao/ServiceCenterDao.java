package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceCenterDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public ServiceCenterDao() {
		DBUtil.loadDriver();
	}
	
	public List getServiceListAdmin() {
		List list = new ArrayList();
		connection = DBUtil.makeConnection();
		String sql = "select * from service "
				+ "order by serviceReNum asc, serviceReCheck asc";
		
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ServiceCenterBean service = new ServiceCenterBean();
				
				service.setServicecenterNum(resultSet.getInt("serviceNum"));
				service.setServicecenterName(resultSet.getString("serviceName"));
				service.setServicecenterTitle(resultSet.getString("serviceTitle"));
				service.setServicecenterContents(resultSet.getString("serviceContents"));
				service.setServicecenterReNum(resultSet.getInt("serviceReNum"));
				service.setServicecenterReCheck(resultSet.getInt("serviceReCheck"));
				
				list.add(service);
			}
			
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public List getServiceList(String userId) {
		List list = new ArrayList();
		connection = DBUtil.makeConnection();
		String sql = "select * from service "
				+ "where serviceName = ? order by serviceReNum asc, serviceReCheck asc";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userId);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ServiceCenterBean service = new ServiceCenterBean();
				
				service.setServicecenterNum(resultSet.getInt("serviceNum"));
				service.setServicecenterName(resultSet.getString("serviceName"));
				service.setServicecenterTitle(resultSet.getString("serviceTitle"));
				service.setServicecenterContents(resultSet.getString("serviceContents"));
				service.setServicecenterReNum(resultSet.getInt("serviceReNum"));
				service.setServicecenterReCheck(resultSet.getInt("serviceReCheck"));
				
				list.add(service);
			}
			
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public ServiceCenterBean getContext(int num) {
		connection = DBUtil.makeConnection();
		String sql = "select serviceName, serviceTitle, serviceContents from Service "
				+ "where serviceNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				ServiceCenterBean bean = new ServiceCenterBean();
				
				bean.setServicecenterNum(num);
				bean.setServicecenterName(resultSet.getString(1));
				bean.setServicecenterTitle(resultSet.getString(2));
				bean.setServicecenterContents(resultSet.getString(3));
				
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public void ServiceWrite(ServiceCenterBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "insert into service values(serviceseq.nextval, ?, ?, ?, serviceseq.nextval, 0)";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bean.getServicecenterName());
			statement.setString(2, bean.getServicecenterTitle());
			statement.setString(3, bean.getServicecenterContents());
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}
	
	public void ServiceReply(ServiceCenterBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "insert into service values(serviceseq.nextval, ?, ?, ?, ?, ?)";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bean.getServicecenterName());
			statement.setString(2, bean.getServicecenterTitle());
			statement.setString(3, bean.getServicecenterContents());
			statement.setInt(4, bean.getServicecenterReNum());
			statement.setInt(5, bean.getServicecenterReCheck());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}
}
