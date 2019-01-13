import java.net.*;
import java.util.Calendar;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

class Client
{
         
           JScrollPane jsp;
          JFrame fr;
          Container cnt;
         JLabel l1, l2;
         static JTextField t1;
        static JTextArea  a1;
         JButton b;
 static InputStream in =null;
static OutputStream out = null;                     
  static Socket soc = null;                   
                void StartServer()
                       {
                       try
                       {
                        Socket    soc = new Socket("127.0.0.1",9090);
	        in=soc.getInputStream();
	       out=soc.getOutputStream();
                       }
                       catch(Exception e){}
                                                                                                                         }                           
   
                    
 
void display()
                     {
                                 fr = new JFrame("Client Side");
                                 cnt = fr.getContentPane();
                                 l1 = new JLabel("Chatting Messages :");
                                 l2 = new JLabel("Type your message here:");
                                 t1 = new JTextField("");
                                 a1 = new JTextArea("");
                                 b = new JButton("SEND");
                                 jsp = new JScrollPane(a1); 
                                 cnt.setLayout(null);
                                 l1.setBounds(20,20,250,50);
                                 jsp.setBounds(170,20,500,150);
                                 l2.setBounds(20,300,250,50);
                                 t1.setBounds(170,300,500,75);
                                 b.setBounds(500,400,100,25);
                                 cnt.add(l1);
                                 cnt.add(t1);
                                 cnt.add(l2);
                                 cnt.add(jsp);
                                 cnt.add(b);
                                 cnt.setBackground(Color.green);
                                 fr.setSize(1000,600);
                                 fr.setVisible(true);
                                 Order o = new Order();
                                 b.addActionListener(o); 
                           }

             
 class Order implements ActionListener
        {
                 public void actionPerformed( ActionEvent e)
                  {
                       try
                           {
                              
                                                                               
                           String cal=calendar();
                           String s1=Client.t1.getText();
                          Client.a1.append("\n");
                          String s = "You sent:- " +s1+ "         ("+cal+")"; 
                          Client.a1.append(s);
                          Client.a1.setEditable(false); 
                          process ();  
                          }
 catch(Exception ee)
                       {
                        System.out.println("Error 2 is :-" + ee);
                       } 


   }
}
void process()              
{
            
                 try
                 { 
                
                String st = Client.t1.getText();                                                   // Get text from t1 TextField and store it into string str.                                                       
                byte b[ ] =  st.getBytes();                                               // Convert it into bytes to write it on output stream.
                Client.out.write(b,0,b.length);                                                  // Write the bytes on output stream.             
                Client.out.write('#');                                                                 // Write ' # ' at last to indicate termination of a particular message.
                 Client.t1.setText("");                                                               // Set the TextField blank again.
                 }
 catch(Exception ee)
                       {
                        System.out.println("Error 3 is :-" + ee);
                       } 

  }

public String calendar()
{
           Calendar now = Calendar.getInstance();
            int h = now.get(Calendar.HOUR_OF_DAY);
            int m = now.get(Calendar.MINUTE);
            int s = now.get(Calendar.SECOND);
            return("" + h + ":" + m + ":" + s);
}


public static void main ( String args [ ] )
       {
          try
                   {              
                         Client s = new Client(); 
                         s.display();
                          s.StartServer();
                       
                                                  while(true)
				{
                                                              String str="";
		                              int r=in.read();
			              while(r!='#')
			                 {
			                       str=str+(char)r;
			                       r=in.read();
			                 }
			
			  String cale=s.calendar();
			str="Server sent:- "+str+"              ("+cale+")";
			

		 a1.append("\n" + str);
                                     a1.setEditable(false);
                                                }   
                 }


catch(Exception ee)
              {
            System.out.println("Error 4 is :-" +ee);
              }
}
}


                 













