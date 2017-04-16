/**
 * @author Mukes
 * Gets readable and writable database and inserts and retrieve data
 */
package rms.manozct.resturantmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Menu;
import rms.manozct.resturantmanagement.model.Order;
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.model.SubMenu;
import rms.manozct.resturantmanagement.util.Util;

public class DbHelper
{
	private SQLiteDatabase database;
	private RmsDb rmsDb;
	Context context;

	public DbHelper(Context context) {
		this.context = context;
		rmsDb = new RmsDb(context);
	}

	public void read() throws SQLException {
		database = rmsDb.getReadableDatabase();
	}

	public void write() {
		database = rmsDb.getWritableDatabase();
	}

	public void close() {
		rmsDb.close();
	}


	// Methods for employee
	public List<Employee> getEmployee(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.EmployeeTbl.EMP_TABLE);
		if (id!=null){
			query.append(" WHERE " + RmsDb.EmployeeTbl.EMP_ID + "=" +String.valueOf(id));
		}
		Cursor myCursor = database.rawQuery(query.toString(), null);
		myCursor.moveToFirst();
		List<Employee> employees = new ArrayList<>();
		while(!myCursor.isAfterLast()){
			Employee emp= new Employee();
			emp.setEmpId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_ID)));
			emp.setEmpName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_NAME)));
			emp.setEmpUserName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_USERNAME)));
			emp.setAddress(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.ADDRESS)));
			emp.setDob(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.DOB))));
			emp.setcNo(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.CONTACT_NO)));
			emp.setSsn(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.SSN)));
			emp.setRole(Role.valueOf(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.POSITION)) ));
			emp.setHireDay(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.HIRE_DAY))));

			employees.add(emp);
			myCursor.moveToNext();
		}
		return employees;
	}

	public Employee getEmployeeFromName(String name,String pswd) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.EmployeeTbl.EMP_TABLE);

		query.append(" WHERE " + RmsDb.EmployeeTbl.EMP_NAME + "='" +name );
		query.append("' and "+RmsDb.EmployeeTbl.EMP_PASSWORD+"='" +pswd+"'");

		Cursor myCursor = database.rawQuery(query.toString(), null);

		if (myCursor.moveToFirst()){
			Employee emp= new Employee();
			emp.setEmpId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_ID)));
			emp.setEmpName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_NAME)));
			emp.setEmpUserName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.EMP_USERNAME)));
			emp.setAddress(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.ADDRESS)));
			emp.setDob(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.DOB))));
			emp.setcNo(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.CONTACT_NO)));
			emp.setSsn(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.SSN)));
			emp.setRole(Role.valueOf(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.POSITION)) ));
			emp.setHireDay(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.EmployeeTbl.HIRE_DAY))));
			return emp;
		}
		return  null;
	}

	public int updateEmployee(int id, Employee employee) throws Exception {
		// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE
		// DATABASE
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.EmployeeTbl.EMP_NAME, employee.getName());
		cv.put(RmsDb.EmployeeTbl.ADDRESS, employee.getAddress());
		//cv.put(RmsDb.DOB, employee.getDob().toString());
		cv.put(RmsDb.EmployeeTbl.CONTACT_NO, employee.getcNo());
		cv.put(RmsDb.EmployeeTbl.SSN, employee.getSsn());
		//cv.put(RmsDb.HIRE_DAY, employee.getHireDay().toString());
		int numRowsAffected = database.update(RmsDb.EmployeeTbl.EMP_TABLE, cv,
				RmsDb.EmployeeTbl.EMP_ID + "=?",
				new String[] { String.valueOf(id)});
		return numRowsAffected;
	}
    public long insertEmployee(Employee employee) throws Exception {
		ContentValues cv = new ContentValues();
		//cv.put(RmsDb.EMP_ID, employee.getEmpId());
		cv.put(RmsDb.EmployeeTbl.EMP_NAME, employee.getName());
		cv.put(RmsDb.EmployeeTbl.EMP_USERNAME, employee.getEmpUserName());
		cv.put(RmsDb.EmployeeTbl.EMP_PASSWORD, employee.getEmpPassword());
		cv.put(RmsDb.EmployeeTbl.ADDRESS, employee.getAddress());
		cv.put(RmsDb.EmployeeTbl.POSITION, String.valueOf(employee.getRole()));
		//cv.put(RmsDb.EmployeeTbl.DOB, "");
		cv.put(RmsDb.EmployeeTbl.DOB, employee.getDob().toString());
		cv.put(RmsDb.EmployeeTbl.CONTACT_NO, employee.getcNo());
		cv.put(RmsDb.EmployeeTbl.SSN, employee.getSsn());
		//cv.put(RmsDb.EmployeeTbl.HIRE_DAY, "");
		cv.put(RmsDb.EmployeeTbl.HIRE_DAY, employee.getHireDay().toString());
		return database.insert(RmsDb.EmployeeTbl.EMP_TABLE, null, cv);
	}
	public void deleteEmployee(Integer empId){
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+RmsDb.EmployeeTbl.EMP_TABLE);
		if (empId!=null){
			query.append(" WHERE " + RmsDb.EmployeeTbl.EMP_ID + "=" +String.valueOf(empId));
		}
		database.execSQL(query.toString());
	}

	// methods for MenuTbl
	public List<Menu> getMenuList(Integer id) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.MenuTbl.MENU_TABLE);
		if (id!=null){

			query.append(" WHERE " + RmsDb.MenuTbl.MENU_ID + "=" +String.valueOf(id));
		}
		Cursor myCursor = database.rawQuery(query.toString(), null);
		myCursor.moveToFirst();
		List<Menu> menulist= new ArrayList<>();

		Menu menuob= null;
		while(!myCursor.isAfterLast())
		{
			menuob= new Menu();
			menuob.setMenuId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.MenuTbl.MENU_ID)));
			menuob.setMenuName(myCursor.getString(myCursor.getColumnIndex(RmsDb.MenuTbl.MENU_NAME)));

            menulist.add(menuob);
			myCursor.moveToNext();

		}

		return menulist;
	}
	public long insertMenu(Menu menu) {
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.MenuTbl.MENU_NAME, menu.getMenuName());
		return database.insert(RmsDb.MenuTbl.MENU_TABLE,null,cv);

	}
	public int updateMenu(int id, Menu menu) throws Exception {
		// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE
		// DATABASE
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.MenuTbl.MENU_NAME, menu.getMenuName());

		int numRowsAffected = database.update(RmsDb.MenuTbl.MENU_TABLE, cv,
				RmsDb.MenuTbl.MENU_ID + "=?",
				new String[] { String.valueOf(id)});
		return numRowsAffected;
	}
	public void deleteMenu(Integer id){
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+RmsDb.MenuTbl.MENU_TABLE);
		if (id!=null){
			query.append(" WHERE " + RmsDb.MenuTbl.MENU_ID + "=" +String.valueOf(id));
		}
		database.execSQL(query.toString());
	}




	// methods for subMenuTable
	public List<SubMenu> getSubMenu(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.SubMenuTbl.SUB_MENU_TABLE);
		if (id!=null)
		{
			query.append(" WHERE " + RmsDb.SubMenuTbl.SUB_MENU_ID + "=" +String.valueOf(id));
		}
		Cursor myCursor = database.rawQuery(query.toString(), null);
		myCursor.moveToFirst();
		List<SubMenu> menulist= new ArrayList<>();

		SubMenu menuob= null;
		while(myCursor.isAfterLast() == false)
		{
			menuob= new SubMenu();
			menuob.setSubMenuId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.SubMenuTbl.SUB_MENU_ID)));
			menuob.setSubMenuName(myCursor.getString(myCursor.getColumnIndex(RmsDb.SubMenuTbl.SUB_MENU_NAME)));
			menuob.setMainMenuId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.SubMenuTbl.MAIN_MENU_ID)));
			menuob.setPrice(myCursor.getDouble(myCursor.getColumnIndex(RmsDb.SubMenuTbl.PRICE)));
			menuob.setImageUrl(myCursor.getString(myCursor.getColumnIndex(RmsDb.SubMenuTbl.SUB_MENU_IMAGE)));

			menulist.add(menuob);
			myCursor.moveToNext();

		}

		return menulist;
	}
	public long insertSubMenu(SubMenu menu) {
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.SubMenuTbl.SUB_MENU_NAME, menu.getSubMenuName());
		cv.put(RmsDb.SubMenuTbl.MAIN_MENU_ID,menu.getMainMenuId());
		cv.put(RmsDb.SubMenuTbl.PRICE, menu.getPrice());
		cv.put(RmsDb.SubMenuTbl.SUB_MENU_IMAGE,menu.getImageUrl());

		return database.insert(RmsDb.SubMenuTbl.SUB_MENU_TABLE,null,cv);


	}
	public int updateSubMenu(int id, SubMenu menu) throws Exception {
		// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE
		// DATABASE
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.SubMenuTbl.SUB_MENU_NAME, menu.getSubMenuName());
		cv.put(RmsDb.SubMenuTbl.MAIN_MENU_ID,menu.getMainMenuId());
		cv.put(RmsDb.SubMenuTbl.PRICE, menu.getPrice());
		cv.put(RmsDb.SubMenuTbl.SUB_MENU_IMAGE,menu.getImageUrl());

		int numRowsAffected = database.update(RmsDb.SubMenuTbl.SUB_MENU_TABLE, cv,
				RmsDb.SubMenuTbl.SUB_MENU_ID + "=?",
				new String[] { String.valueOf(id)});
		return numRowsAffected;
	}
	public void deleteSubMenu(Integer id){
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+ RmsDb.SubMenuTbl.SUB_MENU_TABLE);
		if (id!=null){
			query.append(" WHERE " + RmsDb.SubMenuTbl.SUB_MENU_ID + "=" +String.valueOf(id));
		}
		database.execSQL(query.toString());
	}


	// menthods for order class
	public List<Order> getOrderList(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.OrderTbl.ORDER_TABLE);
		if (id!=null)
		{
			query.append(" WHERE " + RmsDb.OrderTbl.ORDER_ID + "=" +String.valueOf(id));
		}
		Cursor myCursor = database.rawQuery(query.toString(), null);
		myCursor.moveToFirst();
		List<Order> orders= new ArrayList<>();

		Order order= null;
		while(myCursor.isAfterLast() == false)
		{
			order= new Order();
			order.setOrderId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.OrderTbl.ORDER_ID)));
			order.setTableId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.OrderTbl.TABLE_ID)));
			order.setSubMenuId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.OrderTbl.SUBMENU_ID)));
			order.setWaiterId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.OrderTbl.WAITER_ID)));
			order.setOrderDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.OrderTbl.ORDER_DATE)));

			orders.add(order);
			myCursor.moveToNext();

		}

		return orders;
	}
	public long insertOrder(Order order) {
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.OrderTbl.TABLE_ID, order.getTableId());
		cv.put(RmsDb.OrderTbl.WAITER_ID,order.getWaiterId());
		cv.put(RmsDb.OrderTbl.ORDER_DATE, order.getOrderDate());
		cv.put(RmsDb.OrderTbl.SUBMENU_ID,order.getSubMenuId());

		return database.insert(RmsDb.OrderTbl.ORDER_TABLE,null,cv);


	}
	public int updateOrder(int id, Order order) throws Exception {
		// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE
		// DATABASE
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.OrderTbl.TABLE_ID, order.getTableId());
		cv.put(RmsDb.OrderTbl.WAITER_ID,order.getWaiterId());
		cv.put(RmsDb.OrderTbl.ORDER_DATE, order.getOrderDate());
		cv.put(RmsDb.OrderTbl.SUBMENU_ID,order.getSubMenuId());

		int numRowsAffected = database.update(RmsDb.OrderTbl.ORDER_TABLE, cv,
				RmsDb.OrderTbl.ORDER_ID + "=?",
				new String[] { String.valueOf(id)});
		return numRowsAffected;
	}
	public void deleteOrder(Integer id){
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+ RmsDb.OrderTbl.ORDER_TABLE);
		if (id!=null){
			query.append(" WHERE " + RmsDb.OrderTbl.ORDER_ID + "=" +String.valueOf(id));
		}
		database.execSQL(query.toString());
	}


}
