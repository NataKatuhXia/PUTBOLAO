/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vo;

//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author guest-78tdco
 */
public class Jogo {

   
	private int id;
    //private List<Bolao> listaBolao = new ArrayList<>();

    private String timeA;
    private String timeB;

    private int placarTimeA;
    private int placarTimeB;
    
    public Jogo() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the listaBolao
	 */
	/*
	public List<Bolao> getListaBolao() {
		return listaBolao;
	}
	*/
	/**
	 * @param listaBolao the listaBolao to set
	 */
	/*
	public void setListaBolao(List<Bolao> listaBolao) {
		this.listaBolao = listaBolao;
	}
	*/
	/**
	 * @return the timeA
	 */
	public String getTimeA() {
		return timeA;
	}
	/**
	 * @param timeA the timeA to set
	 */
	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}
	/**
	 * @return the timeB
	 */
	public String getTimeB() {
		return timeB;
	}
	/**
	 * @param timeB the timeB to set
	 */
	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}
	/**
	 * @return the placarTimeA
	 */
	public int getPlacarTimeA() {
		return placarTimeA;
	}
	/**
	 * @param placarTimeA the placarTimeA to set
	 */
	public void setPlacarTimeA(int placarTimeA) {
		this.placarTimeA = placarTimeA;
	}
	/**
	 * @return the placarTimeB
	 */
	public int getPlacarTimeB() {
		return placarTimeB;
	}
	/**
	 * @param placarTimeB the placarTimeB to set
	 */
	public void setPlacarTimeB(int placarTimeB) {
		this.placarTimeB = placarTimeB;
	}
}
