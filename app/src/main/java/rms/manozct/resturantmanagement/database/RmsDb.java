package rms.manozct.resturantmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import rms.manozct.resturantmanagement.model.Role;

public final class RmsDb extends SQLiteOpenHelper{

	static final String dbName = "rmsDb";

	//Variables for Employee table
	static final String EMP_TABLE = "empTable";
	static final String EMP_ID = "empId";
	static final String EMP_USERNAME="userName";
	static final String EMP_PASSWORD="password";
	static final String EMP_NAME = "name";
	static final String ADDRESS = "address";
	static final String DOB = "dob";
	static final String CONTACT_NO = "cNo";
	static final String SSN = "ssn";
	static final String HIRE_DAY = "hireDay";
	static final String POSITION="position";

	//Variable for Order Variable
	static final String ORDER_TABLE = "order";
	static final String ORDER_ID = "orderId";

	//Create Query for Employee table
	private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS "+EMP_TABLE+" (" +
												EMP_ID + " INTEGER PRIMARY KEY , " +
												EMP_USERNAME + " TEXT, " +
												EMP_PASSWORD + " TEXT, " +
												EMP_NAME + " TEXT, " +
												ADDRESS + " TEXT, " +
												DOB + " TEXT, " +
												CONTACT_NO + " TEXT, " +
												SSN + " TEXT, " +
												HIRE_DAY + " TEXT, " +
												POSITION + " TEXT" +
												")";

	//Create Query for Employee table
	private static final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS "+ORDER_TABLE+" (" +
			ORDER_ID + " INTEGER PRIMARY KEY , " +

			")";

	Context context;
	RmsDb(Context context) {
		super(context, dbName, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ESTABLISH NEW DATABASE TABLES IF THEY DON'T ALREADY EXIST IN THE DATABASE
		db.execSQL(CREATE_EMPLOYEE_TABLE);
		//db.execSQL(CREATE_ORDER_TABLE);
		System.out.println(CREATE_ORDER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS "+tLevels);
        onCreate(db);
	}
}
