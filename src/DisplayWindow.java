import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
import java.beans.PropertyChangeEvent;

public class DisplayWindow {

	private JFrame frame;
	private VigenereBF vigi;
	private String enc, plaintext;
	private JTextField textField;

	public DisplayWindow(String enc, String plaintext, VigenereBF vigi) {
		this.vigi = vigi;
		this.enc = enc;
		this.plaintext = plaintext;
		initialize(enc, plaintext);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param plaintext 
	 * @param enc 
	 */
	private void initialize(String enc, String plaintext) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 52, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 424, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, -30, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, panel);
		panel.setLayout(new MigLayout("", "[89px][][][][][][][][][][][]", "[23px]"));
		
		JButton btnNewButton = new JButton("Start Attack");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				long startTime = System.currentTimeMillis();
		        vigi.decode();
		        long endTime   = System.currentTimeMillis();
		        long totalTime = endTime - startTime;
		        int temp =  (int) TimeUnit.MILLISECONDS.toMinutes(totalTime);
		        textField.setText(Integer.toString(temp));
			}
		});
		panel.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		
		JButton btnDemoAttack = new JButton("Demo Attack");
		btnDemoAttack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long startTime = System.currentTimeMillis();
		        vigi.decodeDemo("tttttt");
		        System.out.println("0");
		        vigi.decodeDemo("aaaaaa");
		        System.out.println("1");
		        vigi.decodeDemo("turinn");
		        System.out.println("2");
		        vigi.decodeDemo("turing");
		        System.out.println("Bingo?");
		        long endTime   = System.currentTimeMillis();
		        long totalTime = endTime - startTime;
		        int temp =  (int) TimeUnit.MILLISECONDS.toMinutes(totalTime);
		        textField.setText(Integer.toString(temp));
			}
		});
		panel.add(btnDemoAttack, "cell 11 0");
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		sl_panel_1.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, panel_1);
		progressBar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				progressBar.setValue( vigi.getProgress());
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, progressBar, 7, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, panel_1);
		panel_1.add(progressBar);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 77, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 97, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, panel);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setVisible(true);
	}
	
	public void setTextField(String input) {
		textField.setText(input);
	}
}
