package com.Encrypts.goofyturtle;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.Encrypts.goofyturtle.model.Child;
import com.Encrypts.goofyturtle.model.MenuOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

//import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
        private List<MenuOptions> menuOptions = null;
        String response =
                "[{\"CatagoryName\":\"Drones\",\"SiteUrl\":\"category-drones\",\"child\":[{\"categoryName\":\"Drones 1\",\"siteUrl\":\"subcategory-drones-1\"},{\"categoryName\":\"Drones 2\",\"siteUrl\":\"subcategory-drones-2\"},{\"categoryName\":\"Fixed Wing Drone\",\"siteUrl\":\"Fixed-Wing-Drone\"}]},{\"CatagoryName\":\"Pre-School\",\"SiteUrl\":\"category-pre-school\",\"child\":[{\"categoryName\":\"Pre-School 1\",\"siteUrl\":\"subcategory-pre-school-1\"},{\"categoryName\":\"Pre-School 2\",\"siteUrl\":\"subcategory-pre-school-2\"}]},{\"CatagoryName\":\"Remote Controlled\",\"SiteUrl\":\"category-remote-controlle\",\"child\":[{\"categoryName\":\"Remote Controlled 1\",\"siteUrl\":\"subcategory-remote-controlled-1\"},{\"categoryName\":\"Remote Controlled 2\",\"siteUrl\":\"subcategory-remote-controlled-2\"}]},{\"CatagoryName\":\"Interactive\",\"SiteUrl\":\"category-interactive\"},{\"CatagoryName\":\"Building Blocks\",\"SiteUrl\":\"category-building-blocks\",\"child\":[{\"categoryName\":\"Building Blocks 1\",\"siteUrl\":\"subcategory-building-blocks-1\"},{\"categoryName\":\"Building Blocks 2\",\"siteUrl\":\"subcategory-building-block-2\"}]},{\"CatagoryName\":\"STEM & SMART\",\"SiteUrl\":\"category-stem-smart\",\"child\":[{\"categoryName\":\"STEM & SMART 1\",\"siteUrl\":\"subcategory-stem-smart-1\"},{\"categoryName\":\"STEM & SMART 2\",\"siteUrl\":\"subcategory-stem-smart-2\"}]},{\"CatagoryName\":\"Collectibles\",\"SiteUrl\":\"category-collectibles\"},{\"CatagoryName\":\"Puzzles & Games\",\"SiteUrl\":\"category-puzzles-games\",\"child\":[{\"categoryName\":\"Puzzles & Games 1\",\"siteUrl\":\"subcategory-puzzles-game-1\"},{\"categoryName\":\"Rubics Cubes\",\"siteUrl\":\"Testing-Team\"}]},{\"CatagoryName\":\"Dolls\",\"SiteUrl\":\"Dolls\",\"child\":[{\"categoryName\":\"Barbie\",\"siteUrl\":\"Barbie\"}]}]";
        private AppBarConfiguration mAppBarConfiguration;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            NavigationView navView = findViewById(R.id.nav_view);
            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    return navigationItemSelected(menuItem);
                }
            });
            menuOptions = getMenuOption();
            Menu menu = navView.getMenu();
            for (int i = 0; i< menuOptions.size(); i++) {
                menu.add(R.id.menu_group_id, 200 + i, i, menuOptions.get(i).getCatagoryName());
                if (menuOptions.get(i).getChild() != null && !menuOptions.get(i).getChild().isEmpty()) {
                    navView.getMenu().getItem(i).setActionView(R.layout.menu_image);
                }
            }
        }
        private List<MenuOptions> getMenuOption()  {
            MenuOptions[] playerArray = new Gson().fromJson(response,MenuOptions[].class);
            return Arrays.asList(playerArray);
        }
        private Boolean navigationItemSelected(MenuItem menuItem) {
            Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            if (menuItem.getActionView() != null) {
                if (menuItem.getActionView().getBackground() == null) {
                    menuItem.getActionView().setBackgroundColor(Color.BLUE);
                    NavigationView navView = findViewById(R.id.nav_view);
                    navView.getMenu().removeGroup(R.id.menu_group_id);
                    if (menuOptions.get(menuItem.getOrder()).getChild() != null) {
                        List<Child> submenu =  menuOptions.get(menuItem.getOrder()).getChild();
                        for (int i = 0; i< submenu.size(); i++) {
                            navView.getMenu().add(R.id.sub_menu_id, 300 + i, i, submenu.get(i).getCategoryName());
                        }
                    }
                } else {
                    menuItem.getActionView().setBackground(null);
                }
            }
            return true;
        }
        @Override
        public void onBackPressed() {
            NavigationView navView = findViewById(R.id.nav_view);
            DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
            if (navView.getMenu().getItem(0).getGroupId() == R.id.sub_menu_id) {
                navView.getMenu().removeGroup(R.id.sub_menu_id);
                for (int i = 0; i< menuOptions.size(); i++) {
                    navView.getMenu().add(R.id.menu_group_id, 200 + i, i, menuOptions.get(i).getCatagoryName());
                    if (menuOptions.get(i).getChild() != null && !menuOptions.get(i).getChild().isEmpty()) {
                        navView.getMenu().getItem(i).setActionView(R.layout.menu_image);
                    }
                }
            } else if (navView.isShown()) {
                mDrawerLayout.closeDrawers();
            } else
                super.onBackPressed();
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
    }
