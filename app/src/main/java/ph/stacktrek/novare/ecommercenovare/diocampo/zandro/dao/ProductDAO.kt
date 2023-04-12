package ph.stacktrek.novare.ecommercenovare.diocampo.zandro.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import ph.stacktrek.novare.ecommercenovare.diocampo.zandro.model.Product

interface ProductDAO {
    fun addProduct(product: Product)
    fun getProducts(): ArrayList<Product>
    fun updateProduct(product: Product)
    fun deleteProduct(product: Product)
}

class ProductDAOSQLLiteImplementation(private var context: Context): ProductDAO{
    override fun addProduct(product: Product) {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.TABLE_PRODUCT_NAME, product.name)

        db.insert(DatabaseHandler.TABLE_PRODUCT,
            null,
            contentValues)
        db.close()
    }

    @SuppressLint("Recycle")
    override fun getProducts(): ArrayList<Product> {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        val result = ArrayList<Product>()
        val cursor: Cursor?

        val columns =  arrayOf(DatabaseHandler.TABLE_PRODUCT_ID,
            DatabaseHandler.TABLE_PRODUCT_NAME)

        try {
            cursor = db.query(DatabaseHandler.TABLE_PRODUCT,
                columns,
                null,
                null,
                null,
                null,
                null)

        }catch (sqlException: SQLException){
            db.close()
            return result
        }

        var product: Product
        if(cursor.moveToFirst()){
            do{
                product = Product("")
                product.name = cursor.getString(1)
                product.id = cursor.getInt(0).toString()
                result.add(product)
            }while(cursor.moveToNext())
        }
        return result
    }

    override fun updateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(product: Product) {
        TODO("Not yet implemented")
    }

}