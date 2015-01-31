package com.platforngame.handlers;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.platformgame.screens.AbstractScreen;

public class CustomDialog extends Dialog {  
	
	   //static Skin skin;
	   
    public CustomDialog (String title) {  
        super(title, AbstractScreen.getSkin(), "big-text-font" );  
       
        initialize();  
    }  
      
    private void initialize() {  
        padTop(60); // set padding on top of the dialog title  
        getButtonTable().defaults().height(60); // set buttons height  
        setModal(true);  
        setMovable(false);  
       // setResizable(false);  
    }  
  
    @Override  
    public CustomDialog text(String text) {  
        super.text(new Label(text, AbstractScreen.getSkin(), "medium-font"));  
        return this;  
    }  
      
    /**  
     * Adds a text button to the button table.  
     * @param listener the input listener that will be attached to the button.  
     */  
    public CustomDialog button(String buttonText, InputListener listener) {  
        TextButton button = new TextButton(buttonText,AbstractScreen.getSkin());  
        button.addListener(listener);  
        button(button);  
        return this;  
    }  
  
    @Override  
    public float getPrefWidth() {  
        // force dialog width  
        return 300f;  
    }  
  
    @Override  
    public float getPrefHeight() {  
        // force dialog height  
        return 200f;  
    }  
}  
