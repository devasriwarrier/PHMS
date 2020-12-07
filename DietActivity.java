package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import static java.lang.Integer.getInteger;


public class DietActivity extends AppCompatActivity {
    private ArrayList<FoodItem> mFoodList;
    private FoodAdapter mAdapter;

    int calorie_counter;
    int food_cal;


    Button dButtonConfirm;
    Button dButtonContinue;
    ImageView dImageViewBack;
    TextView dTextViewNew;
    TextView dTextViewWeight;
    EditText dEditServe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        initList();
        Spinner spinnerFoods = (findViewById(R.id.choice));
        mAdapter = new FoodAdapter(this,mFoodList);
        spinnerFoods.setAdapter(mAdapter);
        spinnerFoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FoodItem clickedItem = (FoodItem) parent.getItemAtPosition(position);
                String clickedFoodName = clickedItem.getFoodName();
                food_cal = clickedItem.getFoodCal();
                Toast.makeText(DietActivity.this, clickedFoodName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

            dTextViewNew = (TextView)findViewById(R.id.food_here);
            dTextViewNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewerIntent = new Intent(DietActivity.this,NewFoodActivity.class);
                startActivity(NewerIntent);

            }
        });

        dTextViewWeight = (TextView)findViewById(R.id.weight_change);
        dTextViewWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewerIntent = new Intent(DietActivity.this,WeightActivity.class);
                startActivity(NewerIntent);

            }
        });

        dImageViewBack = (ImageView)findViewById(R.id.imageView_backButtonDiet);
        dImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DietToHomeIntent = new Intent(DietActivity.this, HomeActivity.class);
                startActivity(DietToHomeIntent);
            }
        });

        dEditServe = (EditText)findViewById(R.id.serving);
        dButtonConfirm = (Button) findViewById(R.id.Next);
        dButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt( dEditServe.getText().toString() );

                int new_value = food_cal*amount;
                calorie_counter+=new_value;
                String again = Integer.toString(new_value);

                Toast.makeText(DietActivity.this, again + " Servings", Toast.LENGTH_SHORT).show();
            }
        });

        dButtonContinue = (Button) findViewById(R.id.confirm);
        dButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = Integer.toString(calorie_counter);

                Toast.makeText(DietActivity.this, result + " Calories Consumed", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initList() {
        mFoodList = new ArrayList<>();
        mFoodList.add(new FoodItem("Pick", R.drawable.pick, 0));
        mFoodList.add(new FoodItem("Fish", R.drawable.fish,366));
        mFoodList.add(new FoodItem("Steak", R.drawable.steak,679));
        mFoodList.add(new FoodItem("Eggs", R.drawable.eggs,78));
        mFoodList.add(new FoodItem("Salad", R.drawable.salad,417));
        mFoodList.add(new FoodItem("Pizza", R.drawable.pizza,2269));
        mFoodList.add(new FoodItem("Bread", R.drawable.bread,79));
        mFoodList.add(new FoodItem("Soup", R.drawable.soup,85));
        mFoodList.add(new FoodItem("Rice", R.drawable.rice,206));
        mFoodList.add(new FoodItem("Sushi", R.drawable.sushi,250));
        mFoodList.add(new FoodItem("Ravioli", R.drawable.ravioli,187));
        mFoodList.add(new FoodItem("Hot Dog", R.drawable.hotdog,151));
        mFoodList.add(new FoodItem("Twinkies", R.drawable.twinkies,270));
        mFoodList.add(new FoodItem("Cornbread", R.drawable.cornbread,198));
        mFoodList.add(new FoodItem("Chips", R.drawable.chips,1217));
        mFoodList.add(new FoodItem("Beans", R.drawable.beans,670));
        mFoodList.add(new FoodItem("Popcorn", R.drawable.popcorn,380));
        mFoodList.add(new FoodItem("Waffles", R.drawable.waffles,218));
        mFoodList.add(new FoodItem("Pancakes", R.drawable.pancakes,175));
        mFoodList.add(new FoodItem("Wings", R.drawable.wings,203));
        mFoodList.add(new FoodItem("Ribs", R.drawable.ribs,1015));
        mFoodList.add(new FoodItem("BLT", R.drawable.blt,344));
        mFoodList.add(new FoodItem("Pie", R.drawable.pie,296));
        mFoodList.add(new FoodItem("Cookies", R.drawable.cookie,78));
        mFoodList.add(new FoodItem("Nachos", R.drawable.nachos,346));
        mFoodList.add(new FoodItem("Sandwich", R.drawable.sandwich,266));
        mFoodList.add(new FoodItem("Hamburger", R.drawable.hamburger,354));
    }

}

