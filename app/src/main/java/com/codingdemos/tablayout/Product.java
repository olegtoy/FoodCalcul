package com.codingdemos.tablayout;

/**
 * Created by olegtojgildin on 22.04.2018.
 */

public class Product {
    // Labels table name
    public static final String TABLE = "foods";
    public static final String TABLE2 = "userproduct";

    // Labels Table Columns names
    public static final String KEY_ROWID = "_id";
    public static final String KEY_carbhydrates = "Carbohydrates";
    public static final String KEY_fat = "Fat";
    public static final String KEY_protein = "Protein";
    public static final String KEY_name = "Name";
    public static final String KEY_Cal = "Cal";
    public static final String KEY_count = "Count";

    String Name;
    double Carbohydrates;
    double Fat;
    double Protein;
    double Cal;
    double Count;
    int Id;

    public double getCal() {
        return Cal;
    }

    public double getCarbohydrates() {
        return Carbohydrates;
    }

    public double getCount() {
        return Count;
    }

    public double getFat() {
        return Fat;
    }

    public double getProtein() {
        return Protein;
    }

    public String getName() {
        return Name;
    }

    public Product(String Name, double Carbohydrates, double Fat, double Protein)
    {
        this.Name = Name;
        this.Carbohydrates = Carbohydrates;
        this.Fat = Fat;
        this.Protein = Protein;
    }
    public Product(String Name, double Carbohydrates, double Fat, double Protein,double Cal,double Count)
    {
        this.Name = Name;
        this.Carbohydrates = Carbohydrates;
        this.Fat = Fat;
        this.Protein = Protein;
        this.Count=Count;
        this.Cal=Cal;
    }
    public Product(){

    }

}