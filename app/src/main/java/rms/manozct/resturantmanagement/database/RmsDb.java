package rms.manozct.resturantmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import static rms.manozct.resturantmanagement.database.RmsDb.OrderTbl.CREATE_ORDER_TABLE;
import static rms.manozct.resturantmanagement.database.RmsDb.OrderTbl.SUBMENU_ID;

public final class RmsDb extends SQLiteOpenHelper{

	static final String dbName = "rmsDb";

	//Variables for Employee table
	class EmployeeTbl {
		static final String EMP_TABLE = "empTable";
		static final String EMP_ID = "empId";
		static final String EMP_USERNAME = "userName";
		static final String EMP_PASSWORD = "password";
		static final String EMP_NAME = "name";
		static final String ADDRESS = "address";
		static final String DOB = "dob";
		static final String CONTACT_NO = "cNo";
		static final String SSN = "ssn";
		static final String HIRE_DAY = "hireDay";
		static final String POSITION = "position";
		static final String SALARY = "salary";

		//Create Query for Employee table
		static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS "+EMP_TABLE+" (" +
				EMP_ID + " INTEGER PRIMARY KEY , " +
				EMP_USERNAME + " TEXT, " +
				EMP_PASSWORD + " TEXT, " +
				EMP_NAME + " TEXT, " +
				ADDRESS + " TEXT, " +
				DOB + " TEXT, " +
				CONTACT_NO + " TEXT, " +
				SSN + " TEXT, " +
				HIRE_DAY + " TEXT, " +
				SALARY + " TEXT, " +
				POSITION + " TEXT" +
				")";


	}
	//Variable for Order Variable
	public class OrderTbl
	{
		static final String ORDER_TABLE = "orderTable";
		static final String ORDER_ID = "orderId";
		static final String WAITER_ID = "waiterId";
		static final String SUBMENU_ID = "subMenuId";
		static final String TABLE_ID = "tableId";
		static final String ORDER_DATE = "orderDate";


		//Create Query for Order table
		static final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE + " (" +
				ORDER_ID + " INTEGER PRIMARY KEY , " +
				WAITER_ID+ " INTEGER,"+
				SUBMENU_ID+"INTEGER" +
				TABLE_ID+" INTEGER"+
				ORDER_DATE+" TEXT"+

				")";
	}

	public class MenuTbl
	{
		static final String MENU_TABLE="menuTable";
		static final String  MENU_ID="menuId";
		static final String MENU_NAME="menuName";
		//Create Query for MenuTbl table
		private static final String CREATE_MENU_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ MENU_TABLE + " (" +
				MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				MENU_NAME+ " TEXT"+

				")";
	}

	public class SubMenuTbl	{

		static final String SUB_MENU_TABLE="subMenuTable";
		static final String SUB_MENU_ID="subMenuId";
		static final String SUB_MENU_NAME="subMenuName";
		static final String PRICE="price";
		static final String SUB_MENU_IMAGE="imageUrl";
		static final String MAIN_MENU_ID="mainMenuID";
		//Create Query for SubMenuTbl table
		static final String CREATE_SUB_MENU_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ SUB_MENU_TABLE  + " (" +
				SUB_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				MAIN_MENU_ID+ " INTEGER"+
				SUB_MENU_NAME+ " TEXT,"+
				PRICE+" DOUBLE" +
				SUB_MENU_IMAGE+" TEXT"+

				")";
	}


	Context context;

	RmsDb(Context context) {
		super(context, dbName, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ESTABLISH NEW DATABASE TABLES IF THEY DON'T ALREADY EXIST IN THE DATABASE
		db.execSQL(EmployeeTbl.CREATE_EMPLOYEE_TABLE);
		db.execSQL(MenuTbl.CREATE_MENU_TABLE);
		db.execSQL(SubMenuTbl.CREATE_SUB_MENU_TABLE);
		db.execSQL(OrderTbl.CREATE_ORDER_TABLE);
		//db.execSQL(CREATE_ORDER_TABLE);
		System.out.println(EmployeeTbl.CREATE_EMPLOYEE_TABLE);
		System.out.println(MenuTbl.CREATE_MENU_TABLE);
		System.out.println(SubMenuTbl.CREATE_SUB_MENU_TABLE);
		System.out.println(OrderTbl.CREATE_ORDER_TABLE);

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
