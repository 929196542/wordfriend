package edu.hui;

public class Word implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word;
	private Integer frequency;
	
	public Word() {
		
	}
	
    public String getWord(){
        return this.word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public Integer getFrequency(){
        return this.frequency;
    }

    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }    

}
