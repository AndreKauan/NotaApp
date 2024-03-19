/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author Antonelli
 */
public class Materia {
    private String materia;
    private String p1;
    private String p2;
    private String p3 = null;
    private boolean campoP3 = false;
    private String avg;
    
    //Método Construtor
    public Materia(){}
    
    //Setter
    public void setMateria (String _materia) {materia = _materia;}
    public void setP1 (String _p1) {p1 = _p1;}
    public void setP2 (String _p2) {p2 = _p2;}
    public void setP3 (String _p3) {p3 = _p3;}
    public void setCampoP3 (boolean activated) {campoP3 = activated;}
    public void setAvg (String _avg) {avg = _avg;}
    
    //Getter
    public String getMateria() {return materia;}
    public String getP1() {return p1;}
    public String getP2() {return p2;}
    public String getP3() {return p3;}
    public boolean getCampoP3() {return campoP3;}
    public String getAvg() {return avg;}
}
