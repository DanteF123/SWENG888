package com.example.practice3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    // Instantiating and assigning variables
    private static final String DATABASE_NAME = "product_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCT = "product";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SELLER = "seller";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";


    public ProductDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createProductTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);

    }

    // method to be used to create the product table with the necessary fields and data types.
    private String createProductTable(){
        String QUERY_CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_NAME + " TEXT," +
                KEY_SELLER + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_PRICE + " TEXT" +
                ")";
        return QUERY_CREATE_PRODUCT_TABLE;
    }

    public void addProduct(Product product){
        // getting writable instance of databse
        SQLiteDatabase database = this.getWritableDatabase();
        // Content values to persist items in the database
        ContentValues values = new ContentValues();

        // populating the database with the product items.
        values.put(KEY_NAME, product.getName());
        values.put(KEY_SELLER, product.getSeller());
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_PRICE, product.getPrice());
        database.insert(TABLE_PRODUCT, null, values);

        //closing the connection.
        database.close();
    }


    // Method to query the database searching for products based on a certain category. Could be used if user would like to filter on "Seller" for example.
    public List<Product> getProductByCategory (String category){
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + KEY_NAME + " = ?";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, new String[]{category});

        if (cursor.moveToFirst()){
            do {
                Product product = new Product(
                        cursor.getString(1), // NAME
                        cursor.getString(2), // SELLER
                        cursor.getString(3), // DESCRIPTION
                        cursor.getString(4) // PRICE
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return  productList;
    }

    // Method to retrieve all products in the database
    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        // Select * pulls all data

        String selectQuery = "SELECT * FROM "+TABLE_PRODUCT;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        //Cursor starts at the beginning, and navigates through each record
        if (cursor.moveToFirst()){
            do {
                Product product = new Product(
                        cursor.getString(1), // NAME
                        cursor.getString(2), // SELLER
                        cursor.getString(3), // DESCRIPTION
                        cursor.getString(4) // PRICE
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return productList;
    }

    // Method to determine if database is empty by pulling the count of records. If the count is 0 then there is no data in the database
    public boolean isDatabaseEmpty() {
        boolean isEmpty = true;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + TABLE_PRODUCT, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count > 0) {
                isEmpty = false;
            }
            cursor.close();
        }
        return isEmpty;
    }

    //Populating the database with the list of products.
    public void populateProductDatabase(){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        //products used to populate the database

        values = new ContentValues();
        values.put(KEY_NAME, "Bananas");
        values.put(KEY_SELLER, "Wegmans");
        values.put(KEY_DESCRIPTION, "Yellow fruit good for potassium");
        values.put(KEY_PRICE, "49c");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Oranges");
        values.put(KEY_SELLER, "Wegmans");
        values.put(KEY_DESCRIPTION, "Juicy fruit that is very refreshing");
        values.put(KEY_PRICE, "$1");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Grapefruit");
        values.put(KEY_SELLER, "Giant");
        values.put(KEY_DESCRIPTION, "Large and sour fruit");
        values.put(KEY_PRICE, "$1.25");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Mangos");
        values.put(KEY_SELLER, "Redners");
        values.put(KEY_DESCRIPTION, "Tropical and delicious");
        values.put(KEY_PRICE, "60c");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Kiwi");
        values.put(KEY_SELLER, "Redners");
        values.put(KEY_DESCRIPTION, "Somewhat sour");
        values.put(KEY_PRICE, "70c");
        database.insert(TABLE_PRODUCT, null, values);


        values = new ContentValues();
        values.put(KEY_NAME, "Apples");
        values.put(KEY_SELLER, "Wegmans");
        values.put(KEY_DESCRIPTION, "Classic and versatile");
        values.put(KEY_PRICE, "1.22c");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Grapes");
        values.put(KEY_SELLER, "Wegmans");
        values.put(KEY_DESCRIPTION, "Delicious snack");
        values.put(KEY_PRICE, "1.45c");
        database.insert(TABLE_PRODUCT, null, values);

        database.close();
    }


}
