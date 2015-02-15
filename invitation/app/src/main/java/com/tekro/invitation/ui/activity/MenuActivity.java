package com.tekro.invitation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tekro.invitation.R;
import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.network.ContentProvider;

/**
 * Created by Pau on 12/2/2015.
 */
public class MenuActivity extends Activity implements View.OnClickListener {


    //--------------------------
    // Attributes
    //--------------------------

    private TRGuest guest;
    private Button buttonSave;
    private Button buttonMeat;
    private Button buttonFish;
    private Button buttonVeggie;
    private Button buttonChild;
    private Button buttonOther;
    private EditText editTextOther;


    //--------------------------
    // FragmentActivity Methods
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        setListeners();
        setupGuest();
    }

    @Override
    public void onBackPressed() {
        finishWithResult();
    }

    //--------------------------
    // User Interface Methods
    //--------------------------

    private void findViews() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonMeat = (Button) findViewById(R.id.buttonMeat);
        buttonFish = (Button) findViewById(R.id.buttonFish);
        buttonVeggie = (Button) findViewById(R.id.buttonVeggie);
        buttonChild = (Button) findViewById(R.id.buttonChild);
        buttonOther = (Button) findViewById(R.id.buttonOther);
        editTextOther = (EditText) findViewById(R.id.editTextOther);
    }

    private void setupGuest() {
        guest = ContentProvider.getInstance().currentGuest;
        String menuText = null;
        if (guest.menu != null) {
            int menuResID = getResources().getIdentifier("menu_" + guest.menu, "string", getPackageName());
            menuText = menuResID == 0 ? "" : (String) getResources().getText(menuResID);
        }

        setupButtonWithMenu(menuText, buttonMeat);
        setupButtonWithMenu(menuText, buttonFish);
        setupButtonWithMenu(menuText, buttonVeggie);
        setupButtonWithMenu(menuText, buttonChild);
        setupButtonWithMenu(menuText, buttonOther);

        setupEditTextOther();
    }

    private void setupButtonWithMenu(String menuText, Button button) {
        button.setSelected(menuText != null && menuText.equals(button.getText()));
        button.setTextColor(getResources().getColor(button.isSelected() ? R.color.theme_pink : R.color.theme_pink_transparent));
    }

    private void setupEditTextOther() {
        if (buttonOther.isSelected()) {
            editTextOther.setEnabled(true);
            editTextOther.setText(guest.menuOther != null ? guest.menuOther : "");
        } else {
            editTextOther.setEnabled(false);
        }
    }


    //--------------------------
    // User Interaction Methods
    //--------------------------

    private void setListeners() {
        buttonSave.setOnClickListener(this);
        buttonMeat.setOnClickListener(this);
        buttonFish.setOnClickListener(this);
        buttonVeggie.setOnClickListener(this);
        buttonChild.setOnClickListener(this);
        buttonOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View clickedView) {
        Button clickedButton = (Button) clickedView;

        if (clickedButton.equals(buttonSave)) {
            finishWithResult();
        } else if (clickedButton.getText().equals(getString(R.string.menu_meat))) {
            guest.menu = "meat";
        } else if (clickedButton.getText().equals(getString(R.string.menu_fish))) {
            guest.menu = "fish";
        } else if (clickedButton.getText().equals(getString(R.string.menu_veggie))) {
            guest.menu = "veggie";
        } else if (clickedButton.getText().equals(getString(R.string.menu_child))) {
            guest.menu = "child";
        } else if (clickedButton.getText().equals(getString(R.string.menu_other))) {
            guest.menu = "other";
        }

        setupGuest();
    }

    private void finishWithResult() {
        ContentProvider.getInstance().updateGuest(guest);
        finish();
    }
}
