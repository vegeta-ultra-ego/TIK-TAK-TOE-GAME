package tiktaktoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class mygame extends JFrame implements ActionListener{
	JLabel heading,clklabel;
	Font font=new Font("",Font.BOLD,40);
	JPanel mainpanel;
	JButton []btns = new JButton[9]; 
	
	int gamechances[]= {2,2,2,2,2,2,2,2,2} ;
	int activeplayer=0;
	int wps [][]= {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
	};
	int winner =2;
	boolean gameover=false;
   mygame(){
	   super.setTitle("TIK TAK TOE");
	   super.setSize(450,450);
	   ImageIcon icmg=new ImageIcon("src/tiktaktoe/imgs/icon.png");
	   setIconImage(icmg.getImage());
	   CGUI();
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setVisible(true);
   }
   private void CGUI() {
	   this.setLayout(new BorderLayout());
	   
	   heading = new JLabel("TIK TAC TOE");
	   heading.setFont(font);
	   heading.setHorizontalAlignment(SwingConstants.CENTER);
	   
	   this.add(heading,BorderLayout.NORTH);
	   
	   //clk jlabel
	   
	   clklabel = new JLabel("clock");
	   clklabel.setHorizontalAlignment(SwingConstants.CENTER);
	   this.add(clklabel,BorderLayout.SOUTH);
	   
	   
	   Thread t =new Thread(){
	  public void run() {
		  try {
		  while(true) {
			  String datetime = new Date().toLocaleString();
			  clklabel.setText(datetime);
			  Thread.sleep(1000);
		  
		  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
	   };
	   t.start();
	   mainpanel=new JPanel();
	   mainpanel.setLayout(new GridLayout(3,3));
	   
	   for(int i=1;i<=9;i++) {
		 //  JButton btn = new JButton(i+"");
		   JButton btn = new JButton();
		  // btn.setIcon(new ImageIcon("src/imgs/0.png"));
		   btn.setBackground(Color.decode("#90caf9"));
		   btn.setFont(font);
		   mainpanel.add(btn);
		   btn.addActionListener(this);
		   btn.setName(String.valueOf(i-1));
		   }
	   
	   this.add(mainpanel,BorderLayout.CENTER);
	   
   }
   @Override
    public void actionPerformed(ActionEvent e) {
	   JButton currentButton = (JButton)e.getSource();
	   String nameStr=currentButton.getName();
	   int name = Integer.parseInt(nameStr.trim());
	   
	   if(gameover==true) {
		   JOptionPane.showMessageDialog(this, "game already over");
		   return;
	   }
	   if(gamechances[name]==2) {
		   if(activeplayer==1) {
			   currentButton.setIcon(new ImageIcon("src/tiktaktoe/imgs/cross.png"));
			   gamechances[name]=activeplayer;
			   activeplayer=0;
		   }
		   else {
			   currentButton.setIcon(new ImageIcon("src/tiktaktoe/imgs/0.png"));
			   gamechances[name]=activeplayer;
			   activeplayer=1;
			   
			   
		   }
	   }else {
		   JOptionPane.showMessageDialog(this, "Positon Already Occupied");
	   }
	   
	   for(int []temp:wps) {
		   if((gamechances[temp[0]]==gamechances[temp[1]])&&(gamechances[temp[1]]==gamechances[temp[2]])&&gamechances[temp[2]]!=2) {
			   winner = gamechances[temp[0]];
			   gameover=true;
			   JOptionPane.showMessageDialog(null, "player"+winner+"already won");
			   int i = JOptionPane.showConfirmDialog(this, "do you want to play more?");
			   if(i==0) {
				   this.setVisible(false);
				   new mygame();
			   }else if(i==1) {
				   System.exit(2345);
			   }
			   else {
				   
			   }
			   System.out.print(i);
			   break;
		   }
	   }
   int c=0;
   for(int x:gamechances) {
	   if(x==2) {
		   c++;
		   break;
	   }
   }
   if(c==0&&gameover==false) {
	   JOptionPane.showMessageDialog(null, "DRAW!....");
	   int i = JOptionPane.showConfirmDialog(this, "play more?");
	   if(i==0) {
		   this.setVisible(false);
		   new mygame();
	   }else if(i==1) {
		   System.exit(2345);
	   }
	   else {
		   
	   }
	  gameover=true;
	   
   }
   }
   
}
