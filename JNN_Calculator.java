package jnn_calcultor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JNN_Calculator extends JFrame {
    private Container container;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JTextField displayField; //计算结果显示区
    private String lastCommand; //保存+,-,*,/,=命令
    private boolean start; //判断是否为数字的开始
    private double result;  //计算结果
    private boolean divideZero; //记录除数是否为0
    
    public JNN_Calculator(){
    	super("JNN's Calculator");//调用父类有参构造函数
    	divideZero=false;
    	container=getContentPane();
    	layout = new GridBagLayout();
    	container.setLayout(layout);
        constraints = new GridBagConstraints();
        start = true;
        result = 0;
        
        lastCommand = "=";
        displayField = new JTextField(20);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 100;
        constraints.weighty = 100;
        layout.setConstraints(displayField, constraints);
        container.add(displayField);
        ActionListener insert = new  InsertAction();
        
        addButton("7",0,2,1,1,insert);
        addButton("8",0,2,1,1,insert);
        addButton("9",0,2,1,1,insert);
        addButton("/",0,2,1,1,insert);
        addButton("4",0,2,1,1,insert); 
        addButton("5",0,2,1,1,insert);
        addButton("6",0,2,1,1,insert);
        addButton("*",0,2,1,1,insert);
        addButton("1",0,2,1,1,insert);
        addButton("2",0,2,1,1,insert);
        addButton("3",0,2,1,1,insert);
        addButton("-",0,2,1,1,insert);
        addButton("0",0,2,1,1,insert);
        addButton(".",0,2,1,1,insert);
        addButton("+",0,2,1,1,insert);
        addButton("=",0,2,1,1,insert);
        
        this.setResizable(false);
        setSize(189,200);
        setVisible(true);
    }
    
    private void addButton(String label,int row , int column,int width,int height,ActionListener listener){
    	JButton button = new JButton(label);
    	constraints.gridx = row ;
    	constraints.gridy = column;
    	constraints.gridwidth =  width;
    	constraints.gridheight = height;
    	
    	constraints.fill = GridBagConstraints .BOTH;
    	button.addActionListener(listener);
        layout.setConstraints(button, constraints);
        container.add(button);
    }


private class InsertAction implements ActionListener{
    	
	public void actionPerformed(ActionEvent event) {
        String input = event.getActionCommand();
        if(start){
           displayField.setText("");
           start=false;
        }
        if(input.equals("Backspace")) {
           String str = displayField.getText();
           if(str.length() > 0 ) {
        	  displayField.setText(str.substring(0,str.length()-1));
           }else if(input.equals("CE")||input.equals("C")) {
        	  displayField.setText("0");        	
        	  start=true;   
           }else {
        	  displayField.setText(displayField.getText()+input); 
           }
        }
        }
    }

private class CommandAction implements ActionListener {
    
	public void actionPerformed(ActionEvent evt) {
	   String command = evt.getActionCommand();
	   if(start) {
		  lastCommand = command;
	   }else {
		   calculate(Double.parseDouble(displayField.getText()));
		   lastCommand = command;
		   start = true;
	   }
	}
}

   public void calculate(double x) {		   
	  if(lastCommand.equals("+")) {
		  result+=x;
	  }
	  else if(lastCommand.equalsIgnoreCase("-")) {
		  result-=x;
	  }
	  else if(lastCommand.equals("*")) {
		  result*=x;
	  }
	  else if(lastCommand.equals("/")) {
		  if( x-0 <1e12){
			divideZero=true;  
			return ;
		  }
			result/=x;
		  }else if(lastCommand.equals("=")) {
		    if(divideZero=true) {
		       displayField.setText("Division by zero is forbidden.");	
		       divideZero=false;
		       return;
		    }
			 result = x;
		    displayField.setText(""+result);
	  }
   }
   
   public static void main(String[] args){
	   JNN_Calculator calculator = new JNN_Calculator();
	   calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
	   
}



