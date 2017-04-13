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
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.util.Util;

public class DbHelper {
	public SQLiteDatabase database;
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

	public List<Employee> getEmployee(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + RmsDb.EMP_TABLE);
		if (id!=null){
			query.append(" WHERE " + RmsDb.EMP_ID + "=" +String.valueOf(id));
		}
		Cursor myCursor = database.rawQuery(query.toString(), null);
		myCursor.moveToFirst();
		List<Employee> employees = new ArrayList<>();
		while(myCursor.isAfterLast() == false){
			Employee emp= new Employee();
			emp.setEmpId(myCursor.getInt(myCursor.getColumnIndex(RmsDb.EMP_ID)));
			emp.setEmpName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EMP_NAME)));
			emp.setEmpUserName(myCursor.getString(myCursor.getColumnIndex(RmsDb.EMP_USERNAME)));
			emp.setAddress(myCursor.getString(myCursor.getColumnIndex(RmsDb.ADDRESS)));
			emp.setDob(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.DOB))));
			emp.setcNo(myCursor.getString(myCursor.getColumnIndex(RmsDb.CONTACT_NO)));
			emp.setSsn(myCursor.getString(myCursor.getColumnIndex(RmsDb.SSN)));
			emp.setHireDay(Util.convertStringToDate(myCursor.getString(myCursor.getColumnIndex(RmsDb.HIRE_DAY))));

			employees.add(emp);
			myCursor.moveToNext();
		}
		return employees;
	}
	
	public int updateEmployee(int id, Employee employee) throws Exception {
		// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE
		// DATABASE
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.EMP_NAME, employee.getName());
		cv.put(RmsDb.ADDRESS, employee.getAddress());
		//cv.put(RmsDb.DOB, employee.getDob().toString());
		cv.put(RmsDb.CONTACT_NO, employee.getcNo());
		cv.put(RmsDb.SSN, employee.getSsn());
		//cv.put(RmsDb.HIRE_DAY, employee.getHireDay().toString());
		int numRowsAffected = database.update(RmsDb.EMP_TABLE, cv,
				RmsDb.EMP_ID + "=?",
				new String[] { String.valueOf(id)});
		return numRowsAffected;
	}


	public long insertEmployee(Employee employee) throws Exception {
		ContentValues cv = new ContentValues();
		cv.put(RmsDb.EMP_ID, employee.getEmpId());
		cv.put(RmsDb.EMP_NAME, employee.getName());
		cv.put(RmsDb.ADDRESS, employee.getAddress());
		cv.put(RmsDb.DOB, "");
		//cv.put(RmsDb.DOB, employee.getDob().toString());
		cv.put(RmsDb.CONTACT_NO, employee.getcNo());
		cv.put(RmsDb.SSN, employee.getSsn());
		cv.put(RmsDb.HIRE_DAY, "");
		//cv.put(RmsDb.HIRE_DAY, employee.getHireDay().toString());
		return database.insert(RmsDb.EMP_TABLE, null, cv);
	}

	public void deleteEmployee(Integer empId){
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+RmsDb.EMP_TABLE);
		if (empId!=null){
			query.append(" WHERE " + RmsDb.EMP_ID + "=" +String.valueOf(empId));
		}
		database.execSQL(query.toString());
	}

}
