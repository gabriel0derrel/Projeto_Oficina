/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;


/**
 *
 * @author misuka
 */
public class Exemplo{
    
    private int primaryKey = 0;
    private String campo1 = "";
    private float campo2 = 0;

    public Exemplo() {
    }
    
    public Exemplo(int primaryKey, String campo1, float campo2) {
        this.primaryKey = primaryKey;
        this.campo1 = campo1;
        this.campo2 = campo2;
    }
    
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKet(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public float getCampo2() {
        return campo2;
    }

    public void setCampo2(float campo2) {
        this.campo2 = campo2;
    }
    
    
}
