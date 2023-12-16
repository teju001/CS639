package com.example.sandeep
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.Toast
import ProductAdapter
class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: List<Product>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var cartIcon: ImageView
    private val cartItems: MutableList<CartItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productList = getSampleProducts()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList) { product ->
            addToCart(product)
        }
        recyclerView.adapter = productAdapter

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter(newText)
                return true
            }
        })

        cartIcon = findViewById(R.id.cartIcon)

        cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("cartItems", ArrayList(cartItems))
            startActivity(intent)
        }
    }

    private fun getSampleProducts(): List<Product> {
        return listOf(
            Product(1, "cotton Sweater", 39.99, R.drawable.sweater),
            Product(2, "Winter Cap", 15.99, R.drawable.winter_cap),
            Product(3, "Snow Boots", 59.99, R.drawable.snow_boots),
            Product(4, "Sneakers", 49.99, R.drawable.sneakers),
            Product(5, "Socks Combo", 12.99, R.drawable.socks),
            Product(6, "Bag", 29.99, R.drawable.bag),
            Product(7, "Sandals", 34.99, R.drawable.sandals),
            Product(8, "Perfume", 45.99, R.drawable.perfume),
            Product(9,"woolen sweater", 150.63,R.drawable.woolsweater),
            Product(10,"Addidas Socks",180.59,R.drawable.addidas),
            Product(11,"Fogg Perfume",98.23,R.drawable.fogg),
            Product(12,"US polo Sandals ",102.59,R.drawable.uspolo),
            Product(13,"N5 Chanel Perfume",305.12,R.drawable.n5),
            Product(14,"Choclates",36.21,R.drawable.celebrations)

        )
    }
    fun addToCart(product: Product) {
        cartItems.add(CartItem(product.name, product.price, product.imageResourceId))

        val message = "${product.name} added to cart"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
