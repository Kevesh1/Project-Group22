package budgetapp.controller;

import budgetapp.model.Category;
import budgetapp.model.CategoryItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {


    private ArrayList<CategoryItem> list = new ArrayList<>();
    private CategoryController cc;



    public MainController() throws IOException {
        cc = new CategoryController(this);
    }

    public void initialize(){
        initiateCategories();
    }

    private void initiateCategories(){
        for(Category category : Category.values()){
            int i = 0;
            i++;
            list.add(new CategoryItem(5000, null, category.valueOf(category.name())));
            cc.setLabels(list.get(i));

        }

    }

    public ArrayList<CategoryItem> getCategories(){
        return this.list;
    }

}
