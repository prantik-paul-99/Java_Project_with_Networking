/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnake;

import javafx.scene.image.Image;

/**
 *
 * @author ASUS
 */
public class Player {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public int getPos_row() {
        return pos_row;
    }

    public void setPos_row(int pos_row) {
        this.pos_row = pos_row;
    }

    public int getPos_col() {
        return pos_col;
    }

    public void setPos_col(int pos_col) {
        this.pos_col = pos_col;
    }
    private String name;
    private Boolean turn;
    private int pos_row, pos_col;
    private Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Player(String name, Boolean turn, int pos_row, int pos_col, Image img) {
        this.name = name;
        this.turn = turn;
        this.pos_row = pos_row;
        this.pos_col = pos_col;
        this.img = img;
    }

    
    
    
    
   
}
