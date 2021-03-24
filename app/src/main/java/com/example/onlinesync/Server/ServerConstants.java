package com.example.onlinesync.Server;

import android.provider.BaseColumns;


//constants used for database
public interface ServerConstants extends BaseColumns {
	//public static final String SERVER_ADDRESS = "http://er	p.mdimembrane.com/HMS_API/";
//	String SERVER_ADDRESS = "http://192.51.10.101/HMS_API/";
	//String SERVER_ADDRESS = "http://116.206.99.84/HMS_API/";					//main IP
//	String SERVER_ADDRESS = "http://192.51.11.84/HMS_API/";					//main IP
//	String TEST= SERVER_ADDRESS + "testing.php";

	String SERVER_ADDRESS = "http://ds.accounts.mdi/api/";					//main IP
//	String SERVER_ADDRESS = "http://localerp.mdi/api/";
//	String SERVER_ADDRESS = "http://172.51.11.100/localerp.mdi/api/";
	String TEST= SERVER_ADDRESS + "main_api.php";
	String MACHINE= SERVER_ADDRESS + "api_machine_parameters.php";
	String LOGIN= SERVER_ADDRESS + "loginfile.php";


}
