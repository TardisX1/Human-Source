package pkh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class admin_listener implements ActionListener{
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//1基本信息
		if (e.getSource() == admin.jb1[0]){//1查

			admin.title[0].clear();
			admin.data[0].clear();
			admin.title[0].add("职工号");
			admin.title[0].add("密码");
			admin.title[0].add("姓名");
			admin.title[0].add("性别");
			admin.title[0].add("职位级别");
			admin.title[0].add("部门号");
			admin.title[0].add("职位号");
			admin.title[0].add("邮箱");
			admin.title[0].add("电话");
			admin.title[0].add("入职时间");
			admin.title[0].add("是否离职");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Employee where "; 
			boolean flag=false;
			if(!(admin.jtf0[0].getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf0[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf0[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="name='"+admin.jtf0[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf0[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+admin.jtf0[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf0[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_id='"+admin.jtf0[3].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf0[4].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_id='"+admin.jtf0[4].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Employee;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Password"));
						v.add(rs.getString("Name"));
						v.add(rs.getString("Sex"));
						v.add(rs.getString("Position_rank"));
						v.add(rs.getString("Branch_id"));
						v.add(rs.getString("Position_id"));
						v.add(rs.getString("Email"));
						v.add(rs.getString("Phone"));
						v.add(rs.getString("Hiredate"));
						v.add(rs.getString("Service_state"));
						admin.data[0].add(v);
					}
					admin.data[0].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[0].setDataVector(admin.data[0], admin.title[0]);
		}
		if(e.getSource()==admin.jb2[0]){//2修
			int row = admin.jt[0].getSelectedRow();
			String[] change=new String[11];
			String[] in={"Employee_id","Password","Name","Sex","Position_rank","Branch_id",
					"Position_id","Email","Phone","Hiredate","Service_state"	};
			boolean[] input=new boolean[11];
			for(int i=0;i<change.length;i++){
				if(admin.jt[0].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[0].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Employee set Password='"+change[1]+"'";
				for(int i=2;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Employee_id='"+change[0]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[0]){//3添
			int row = admin.jt[0].getSelectedRow();
			String[] add=new String[11];
			boolean[] input=new boolean[11];
			for(int i=0;i<add.length;i++){
				if(admin.jt[0].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[0].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Employee values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
			//	System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		
	//公司信息
		//2部门信息
		if (e.getSource() == admin.jb1[1]){//1查
			admin.title[1].clear();
			admin.data[1].clear();
			admin.title[1].add("部门号");
			admin.title[1].add("部门名");
			admin.title[1].add("经理号");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Branch where "; 
			boolean flag=false;
			if(!(admin.jtf1[0].getText().length()<1) ){
				sql+="Branch_id='"+admin.jtf1[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf1[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_name='"+admin.jtf1[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf1[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Manager_id='"+admin.jtf1[2].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Branch;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Branch_id"));
						v.add(rs.getString("Branch_name"));
						v.add(rs.getString("Manager_id"));
						admin.data[1].add(v);
					}
					admin.data[1].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[1].setDataVector(admin.data[1], admin.title[1]);
		}
		if(e.getSource()==admin.jb2[1]){//2修
			int row = admin.jt[1].getSelectedRow();
			String[] change=new String[3];
			String[] in={"Branch_id","Branch_name","Manager_id"	};
			boolean[] input=new boolean[3];
			for(int i=0;i<change.length;i++){
				if(admin.jt[1].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[1].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Branch set Branch_name='"+change[1]+"'";
				for(int i=2;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Branch_id='"+change[0]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 =admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[1]){//3添
			int row = admin.jt[1].getSelectedRow();
			String[] add=new String[3];
			boolean[] input=new boolean[3];
			for(int i=0;i<add.length;i++){
				if(admin.jt[1].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[1].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Branch values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		//3职位信息
		if (e.getSource() == admin.jb1[2]){
			admin.title[2].clear();
			admin.data[2].clear();
			admin.title[2].add("职位号");
			admin.title[2].add("职位等级");
			admin.title[2].add("职位名");
			admin.title[2].add("部门号");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Position where "; 
			boolean flag=false;
			if(!(admin.jtf2[0].getText().length()<1) ){
				sql+="Position_id='"+admin.jtf2[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf2[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+admin.jtf2[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf2[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_name='"+admin.jtf2[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf2[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_id='"+admin.jtf2[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Position;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Position_id"));
						v.add(rs.getString("Position_Rank"));
						v.add(rs.getString("Position_name"));
						v.add(rs.getString("Branch_id"));
						admin.data[2].add(v);
					}
					admin.data[2].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[2].setDataVector(admin.data[2], admin.title[2]);
		}
		if(e.getSource()==admin.jb2[2]){//2修
			int row = admin.jt[2].getSelectedRow();
			String[] change=new String[4];
			String[] in={"Position_id","Position_Rank","Position_name","Branch_id"	};
			boolean[] input=new boolean[4];
			for(int i=0;i<change.length;i++){
				if(admin.jt[2].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[2].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Position set Position_Rank='"+change[1]+"'";
				for(int i=2;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Position_id='"+change[0]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[2]){//3添
			int row = admin.jt[2].getSelectedRow();
			String[] add=new String[4];
			boolean[] input=new boolean[4];
			for(int i=0;i<add.length;i++){
				if(admin.jt[2].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[2].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Position values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		
	//员工信息
		//4培训信息
		if (e.getSource() == admin.jb1[3]){
			admin.title[3].clear();
			admin.data[3].clear();
			admin.title[3].add("员工号");
			admin.title[3].add("培训号");
			admin.title[3].add("培训名");
			admin.title[3].add("成绩");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Employee_Course where "; 
			boolean flag=false;
			if(!(admin.jtf3[0].getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf3[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf3[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Course_id='"+admin.jtf3[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf3[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Course_name='"+admin.jtf3[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf3[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Score='"+admin.jtf3[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Employee_Course;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Course_id"));
						v.add(rs.getString("Course_name"));
						v.add(rs.getString("Score"));
						admin.data[3].add(v);
					}
					admin.data[3].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[3].setDataVector(admin.data[3], admin.title[3]);
		}
		if(e.getSource()==admin.jb2[3]){//2修
			int row = admin.jt[3].getSelectedRow();
			String[] change=new String[4];
			String[] in={"Empolyee_id","Course_id","Course_name","Score"	};
			boolean[] input=new boolean[4];
			for(int i=0;i<change.length;i++){
				if(admin.jt[3].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[3].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Employee_Course set Course_name='"+change[2]+"'";
				for(int i=3;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Employee_id='"+change[0]+"' and Course_id='"+change[1]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[3]){//3添
			int row = admin.jt[3].getSelectedRow();
			String[] add=new String[4];
			boolean[] input=new boolean[4];
			for(int i=0;i<add.length;i++){
				if(admin.jt[3].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[3].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Employee_Course values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		//5技能信息
		if (e.getSource() == admin.jb1[4]){
			admin.title[4].clear();
			admin.data[4].clear();
			admin.title[4].add("员工号");
			admin.title[4].add("技能号");
			admin.title[4].add("技能名");
			admin.title[4].add("熟练度");
					
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Employee_Skill where "; 
			boolean flag=false;
			if(!(admin.jtf4[0].getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf4[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf4[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Skill_id='"+admin.jtf4[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf4[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Skill_name='"+admin.jtf4[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf4[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="EXP='"+admin.jtf4[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Employee_Skill;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
					
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Skill_id"));
						v.add(rs.getString("Skill_name"));
						v.add(rs.getString("EXP"));
						admin.data[4].add(v);
					}
					admin.data[4].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[4].setDataVector(admin.data[4], admin.title[4]);
		}
		if(e.getSource()==admin.jb2[4]){//2修
			int row = admin.jt[4].getSelectedRow();
			String[] change=new String[4];
			String[] in={"Empolyee_id","Skill_id","Skill_name","EXP"	};
			boolean[] input=new boolean[4];
			for(int i=0;i<change.length;i++){
				if(admin.jt[4].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[4].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Employee_Skill set Skill_name='"+change[2]+"'";
				for(int i=3;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Employee_id='"+change[0]+"' and Skill_id='"+change[1]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[4]){//3添
			int row = admin.jt[4].getSelectedRow();
			String[] add=new String[4];
			boolean[] input=new boolean[4];
			for(int i=0;i<add.length;i++){
				if(admin.jt[4].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[4].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Employee_Skill values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		
	//6工资信息
		if (e.getSource() == admin.jb1[5]){

			admin.title[5].clear();
			admin.data[5].clear();
			admin.title[5].add("职工号");
			admin.title[5].add("职位级别");
			admin.title[5].add("基本工资");
			admin.title[5].add("业绩");
			admin.title[5].add("奖金");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Salary where "; 
			boolean flag=false;
			if(!(admin.jtf5[0].getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf5[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf5[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+admin.jtf5[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf5[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Base_Salary='"+admin.jtf5[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf5[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Achievement='"+admin.jtf5[3].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf5[4].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Bonus='"+admin.jtf5[4].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Salary;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Position_rank"));
						v.add(rs.getString("Base_Salary"));
						v.add(rs.getString("Achievement"));
						v.add(rs.getString("Bonus"));
						admin.data[5].add(v);
					}
					admin.data[5].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[5].setDataVector(admin.data[5], admin.title[5]);
		}
		if(e.getSource()==admin.jb2[5]){//2修
			int row = admin.jt[5].getSelectedRow();
			String[] change=new String[5];
			String[] in={"Empolyee_id","Position_Rank","Base_salary","Achievement","Bonus"	};
			boolean[] input=new boolean[5];
			for(int i=0;i<change.length;i++){
				if(admin.jt[5].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[5].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Salary set Position_Rank='"+change[1]+"'";
				for(int i=2;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Employee_id='"+change[0]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[5]){//3添
			int row = admin.jt[5].getSelectedRow();
			String[] add=new String[5];
			boolean[] input=new boolean[5];
			for(int i=0;i<add.length;i++){
				if(admin.jt[5].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[5].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Salary values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		
	//7签到
		//1查询签到
		if (e.getSource() == admin.jb1[6]){

			admin.title[6].clear();
			admin.data[6].clear();
			admin.title[6].add("职工号");
			admin.title[6].add("年");
			admin.title[6].add("月");
			admin.title[6].add("日");
			admin.title[6].add("时间");
			admin.title[6].add("是否迟到");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.sign where "; 
			boolean flag=false;
			if(!(admin.jtf6[0].getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf6[0].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf6[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="year='"+admin.jtf6[1].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf6[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="month='"+admin.jtf6[2].getText()+"'";
				flag=true;
			}
			if(!(admin.jtf6[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="day='"+admin.jtf6[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.sign;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("year"));
						v.add(rs.getString("month"));
						v.add(rs.getString("day"));
						v.add(rs.getString("time"));
						v.add(rs.getString("late"));
						admin.data[6].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[6].setDataVector(admin.data[6], admin.title[6]);
		}
		
		//2添加签到
		if(e.getSource()==admin.sign){
			try
			{
				admin.conn = connect.getConnection();
				Calendar cal=Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);
				int month=cal.get(Calendar.MONTH)+1;
				int day=cal.get(Calendar.DATE);
				String sql0="select * from dbo.sign where Employee_id='"+login.id+"' and year='"+
				year+"' and month='"+month+"' and day='"+day+"';";
				//System.out.println(sql0);
				try {
					admin.st = admin.conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs;
		
				rs = admin.st.executeQuery(sql0);
					
				if(rs.next())
				{
					JFrame newFrame = new JFrame();
					newFrame.setLocation(50,200);
					JOptionPane.showMessageDialog(newFrame.getContentPane(),
							"当天已签到！", "操作", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
			    	int hour = cal.get(Calendar.HOUR_OF_DAY); 
				    int minute = cal.get(Calendar.MINUTE); 
				    int second = cal.get(Calendar.SECOND); 
				    String late="";
				    if((hour>=8&&hour<=12)||(hour>=14&&hour<=18))
				    	late="迟到";
				    String sql1 = "insert into dbo.sign values ('"+login.id+"','"+year+"','"+month+
				    		"','"+day+"','"+hour+":"+minute+":"+second+"','"+late+"');";
				    //System.out.println(sql1);
				    PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				    preparedStatement1.executeUpdate();
				
				    JFrame newFrame = new JFrame();
				    newFrame.setLocation(50,200);
				    JOptionPane.showMessageDialog(newFrame.getContentPane(),
						"签到成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
				    }
				admin.conn.close();
				
			} catch (SQLException e2) {

				e2.printStackTrace();
			} catch (Exception e3) {

				e3.printStackTrace();

			}
			
		}
		//3考勤查询修改添加
		if (e.getSource() == admin.jb1[7]){//1查
			admin.title[7].clear();
			admin.data[7].clear();
			admin.title[7].add("职工号");
			admin.title[7].add("迟到次数");
			admin.title[7].add("缺勤次数");
			admin.title[7].add("罚款");
			
			admin.conn = connect.getConnection();
			String sql= "select * from dbo.Attendance where "; 
			boolean flag=false;
			if(!(admin.jtf7.getText().length()<1) ){
				sql+="Employee_id='"+admin.jtf7.getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Attendance;";
			}
			try {
				admin.st = admin.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = admin.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Late"));
						v.add(rs.getString("Absence"));
						v.add(rs.getString("Fine"));
						admin.data[7].add(v);
					}
					admin.data[7].add(new Vector());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					admin.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				admin.dtm[7].setDataVector(admin.data[7], admin.title[7]);
		}
		if(e.getSource()==admin.jb2[7]){//2修
			int row = admin.jt[7].getSelectedRow();
			String[] change=new String[4];
			String[] in={"Employee_id","Late","Absence","Fine"	};
			boolean[] input=new boolean[4];
			for(int i=0;i<change.length;i++){
				if(admin.jt[7].getValueAt(row, i)!=null){
					input[i]=true;
					change[i]=admin.jt[7].getValueAt(row, i)+"";
			//		System.out.println(change[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "update dbo.Attendance set Employee_id='"+change[0]+"'";
				for(int i=1;i<change.length;i++){
					if(input[i]==true)
						sql1+=","+in[i]+"='"+change[i]+"'";
					
				}
				sql1+=" where Employee_id='"+change[0]+"';";
         //		System.out.println(sql1);
				PreparedStatement preparedStatement1 =admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();

				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"修改信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==admin.jb3[7]){//3添
			int row = admin.jt[7].getSelectedRow();
			String[] add=new String[4];
			boolean[] input=new boolean[4];
			for(int i=0;i<add.length;i++){
				if(admin.jt[7].getValueAt(row, i)!=null){
					input[i]=true;
					add[i]=admin.jt[7].getValueAt(row, i)+"";
			//		System.out.println(add[i]);
				}
			}
			try
			{
				admin.conn = connect.getConnection();
				String sql1 = "insert into dbo.Attendance values ('"+add[0]+"'";
				for(int i=1;i<add.length;i++){
					if(input[i]==true)
						sql1+=",'"+add[i]+"'";
					else 
						sql1+=",null";
					
				}
				sql1+=");";
		//		System.out.println(sql1);
				PreparedStatement preparedStatement1 = admin.conn.prepareStatement(sql1);
				preparedStatement1.executeUpdate();
				
				admin.conn.close();
				
				} catch (SQLException e2) {

				e2.printStackTrace();

			} catch (Exception e3) {

				e3.printStackTrace();

			}
			JFrame newFrame = new JFrame();
			newFrame.setLocation(50,200);
			JOptionPane.showMessageDialog(newFrame.getContentPane(),
					"添加信息成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
}
