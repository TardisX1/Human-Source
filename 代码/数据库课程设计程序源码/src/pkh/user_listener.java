package pkh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class user_listener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//1基本信息
		if (e.getSource() == user.jb1[0]){

			user.title[0].clear();
			user.data[0].clear();
			user.title[0].add("职工号");
			user.title[0].add("姓名");
			user.title[0].add("性别");
			user.title[0].add("职位级别");
			user.title[0].add("部门号");
			user.title[0].add("职位号");
			user.title[0].add("邮箱");
			user.title[0].add("电话");
			user.title[0].add("入职时间");
			user.title[0].add("是否离职");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Employee where "; 
			boolean flag=false;
			if(!(user.jtf0[0].getText().length()<1) ){
				sql+="Employee_id='"+user.jtf0[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf0[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="name='"+user.jtf0[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf0[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+user.jtf0[2].getText()+"'";
				flag=true;
			}
			if(!(user.jtf0[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_id='"+user.jtf0[3].getText()+"'";
				flag=true;
			}
			if(!(user.jtf0[4].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_id='"+user.jtf0[4].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Employee;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Name"));
						v.add(rs.getString("Sex"));
						v.add(rs.getString("Position_rank"));
						v.add(rs.getString("Branch_id"));
						v.add(rs.getString("Position_id"));
						v.add(rs.getString("Email"));
						v.add(rs.getString("Phone"));
						v.add(rs.getString("Hiredate"));
						v.add(rs.getString("Service_state"));
						user.data[0].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[0].setDataVector(user.data[0], user.title[0]);
		}
	//公司信息
		//2部门信息
		if (e.getSource() == user.jb1[1]){
			user.title[1].clear();
			user.data[1].clear();
			user.title[1].add("部门号");
			user.title[1].add("部门名");
			user.title[1].add("经理号");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Branch where "; 
			boolean flag=false;
			if(!(user.jtf1[0].getText().length()<1) ){
				sql+="Branch_id='"+user.jtf1[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf1[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_name='"+user.jtf1[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf1[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Manager_id='"+user.jtf1[2].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Branch;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Branch_id"));
						v.add(rs.getString("Branch_name"));
						v.add(rs.getString("Manager_id"));
						user.data[1].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[1].setDataVector(user.data[1], user.title[1]);
		}
		//3职位信息
		if (e.getSource() == user.jb1[2]){
			user.title[2].clear();
			user.data[2].clear();
			user.title[2].add("职位号");
			user.title[2].add("职位等级");
			user.title[2].add("职位名");
			user.title[2].add("部门号");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Position where "; 
			boolean flag=false;
			if(!(user.jtf2[0].getText().length()<1) ){
				sql+="Position_id='"+user.jtf2[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf2[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+user.jtf2[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf2[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_name='"+user.jtf2[2].getText()+"'";
				flag=true;
			}
			if(!(user.jtf2[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Branch_id='"+user.jtf2[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Position;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Position_id"));
						v.add(rs.getString("Position_Rank"));
						v.add(rs.getString("Position_name"));
						v.add(rs.getString("Branch_id"));
						user.data[2].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[2].setDataVector(user.data[2], user.title[2]);
		}
		
		
	//员工信息
		//4培训信息
		if (e.getSource() == user.jb1[3]){
			user.title[3].clear();
			user.data[3].clear();
			user.title[3].add("员工号");
			user.title[3].add("培训号");
			user.title[3].add("培训名");
			user.title[3].add("成绩");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Employee_Course where "; 
			boolean flag=false;
			if(!(user.jtf3[0].getText().length()<1) ){
				sql+="Employee_id='"+user.jtf3[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf3[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Course_id='"+user.jtf3[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf3[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Course_name='"+user.jtf3[2].getText()+"'";
				flag=true;
			}
			if(!(user.jtf3[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Score='"+user.jtf3[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Employee_Course;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Course_id"));
						v.add(rs.getString("Course_name"));
						v.add(rs.getString("Score"));
						user.data[3].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[3].setDataVector(user.data[3], user.title[3]);
		}
		//5技能信息
				if (e.getSource() == user.jb1[4]){
					user.title[4].clear();
					user.data[4].clear();
					user.title[4].add("员工号");
					user.title[4].add("技能号");
					user.title[4].add("技能名");
					user.title[4].add("熟练度");
					
					user.conn = connect.getConnection();
					String sql= "select * from dbo.Employee_Skill where "; 
					boolean flag=false;
					if(!(user.jtf4[0].getText().length()<1) ){
						sql+="Employee_id='"+user.jtf4[0].getText()+"'";
						flag=true;
					}
					if(!(user.jtf4[1].getText().length()<1) ){
						if(flag){
							sql+=" and ";
						}
						sql+="Skill_id='"+user.jtf4[1].getText()+"'";
						flag=true;
					}
					if(!(user.jtf4[2].getText().length()<1) ){
						if(flag){
							sql+=" and ";
						}
						sql+="Skill_name='"+user.jtf4[2].getText()+"'";
						flag=true;
					}
					if(!(user.jtf4[3].getText().length()<1) ){
						if(flag){
							sql+=" and ";
						}
						sql+="EXP='"+user.jtf4[3].getText()+"'";
						flag=true;
					}
					sql+=";";
					if(flag==false){//全选
						sql="select * from dbo.Employee_Skill;";
					}
					try {
						user.st = user.conn.createStatement();
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					ResultSet rs;
					
						try {
							rs = user.st.executeQuery(sql);
							while(rs.next())
							{
								Vector v = new Vector();
								v.add(rs.getString("Employee_id"));
								v.add(rs.getString("Skill_id"));
								v.add(rs.getString("Skill_name"));
								v.add(rs.getString("EXP"));
								user.data[4].add(v);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							user.conn.close();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						user.dtm[4].setDataVector(user.data[4], user.title[4]);
				}
		
		
	//6工资信息
		if (e.getSource() == user.jb1[5]){

			user.title[5].clear();
			user.data[5].clear();
			user.title[5].add("职工号");
			user.title[5].add("职位级别");
			user.title[5].add("基本工资");
			user.title[5].add("业绩");
			user.title[5].add("奖金");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Salary where "; 
			boolean flag=false;
			if(!(user.jtf5[0].getText().length()<1) ){
				sql+="Employee_id='"+user.jtf5[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf5[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Position_Rank='"+user.jtf5[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf5[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Base_Salary='"+user.jtf5[2].getText()+"'";
				flag=true;
			}
			if(!(user.jtf5[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Achievement='"+user.jtf5[3].getText()+"'";
				flag=true;
			}
			if(!(user.jtf5[4].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="Bonus='"+user.jtf5[4].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Salary;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Position_rank"));
						v.add(rs.getString("Base_Salary"));
						v.add(rs.getString("Achievement"));
						v.add(rs.getString("Bonus"));
						user.data[5].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[5].setDataVector(user.data[5], user.title[5]);
		}
		
		
	//7签到
		//1查询签到
		if (e.getSource() == user.jb1[6]){

			user.title[6].clear();
			user.data[6].clear();
			user.title[6].add("职工号");
			user.title[6].add("年");
			user.title[6].add("月");
			user.title[6].add("日");
			user.title[6].add("时间");
			user.title[6].add("是否迟到");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.sign where "; 
			boolean flag=false;
			if(!(user.jtf6[0].getText().length()<1) ){
				sql+="Employee_id='"+user.jtf6[0].getText()+"'";
				flag=true;
			}
			if(!(user.jtf6[1].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="year='"+user.jtf6[1].getText()+"'";
				flag=true;
			}
			if(!(user.jtf6[2].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="month='"+user.jtf6[2].getText()+"'";
				flag=true;
			}
			if(!(user.jtf6[3].getText().length()<1) ){
				if(flag){
					sql+=" and ";
				}
				sql+="day='"+user.jtf6[3].getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.sign;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("year"));
						v.add(rs.getString("month"));
						v.add(rs.getString("day"));
						v.add(rs.getString("time"));
						v.add(rs.getString("late"));
						user.data[6].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[6].setDataVector(user.data[6], user.title[6]);
		}
		
		//2添加签到
		if(e.getSource()==user.sign){
			try
			{
				user.conn = connect.getConnection();
				Calendar cal=Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);
				int month=cal.get(Calendar.MONTH)+1;
				int day=cal.get(Calendar.DATE);
				String sql0="select * from dbo.sign where Employee_id='"+login.id+"' and year='"+
				year+"' and month='"+month+"' and day='"+day+"';";
				//System.out.println(sql0);
				try {
					user.st = user.conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs;
		
				rs = user.st.executeQuery(sql0);
					
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
				    PreparedStatement preparedStatement1 = user.conn.prepareStatement(sql1);
				    preparedStatement1.executeUpdate();
				
				    JFrame newFrame = new JFrame();
				    newFrame.setLocation(50,200);
				    JOptionPane.showMessageDialog(newFrame.getContentPane(),
						"签到成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
				    }
				user.conn.close();
				
			} catch (SQLException e2) {

				e2.printStackTrace();
			} catch (Exception e3) {

				e3.printStackTrace();

			}
			
		}
		//3查询考勤
		if (e.getSource() == user.jb1[7]){//1查
			user.title[7].clear();
			user.data[7].clear();
			user.title[7].add("职工号");
			user.title[7].add("迟到次数");
			user.title[7].add("缺勤次数");
			user.title[7].add("罚款");
			
			user.conn = connect.getConnection();
			String sql= "select * from dbo.Attendance where "; 
			boolean flag=false;
			if(!(user.jtf7.getText().length()<1) ){
				sql+="Employee_id='"+user.jtf7.getText()+"'";
				flag=true;
			}
			sql+=";";
			if(flag==false){//全选
				sql="select * from dbo.Attendance;";
			}
			try {
				user.st = user.conn.createStatement();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet rs;
			
				try {
					rs = user.st.executeQuery(sql);
					while(rs.next())
					{
						Vector v = new Vector();
						v.add(rs.getString("Employee_id"));
						v.add(rs.getString("Late"));
						v.add(rs.getString("Absence"));
						v.add(rs.getString("Fine"));
						user.data[7].add(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				user.dtm[7].setDataVector(user.data[7], user.title[7]);
		}
		
		
	//补充
		//修改下级员工奖金
		if (e.getSource() == user.setBonus){
			try
			{
				user.conn = connect.getConnection();
				String sql0="select * from dbo.Employee where Employee_id='"+login.id+"';";
				//System.out.println(sql0);
				//System.out.println(login.id);
				try {
					user.st = user.conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs;
		
				rs = user.st.executeQuery(sql0);
					
				int row = user.jt[5].getSelectedRow();
				//System.out.println(row);
			if(rs.next()){
				int userRank=Integer.parseInt(rs.getString("Position_rank"));
				int changeRank=Integer.valueOf((String) user.jt[5].getValueAt(row, 1));
				if(userRank>=changeRank)
				{
					JFrame newFrame = new JFrame();
					newFrame.setLocation(50,200);
					JOptionPane.showMessageDialog(newFrame.getContentPane(),
							"没有权限修改该员工的奖金！", "操作", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
					if(user.jt[5].getValueAt(row, 4)!=null){
					    String sql1 = "update dbo.Salary set Bonus='"+user.jt[5].getValueAt(row, 4)
							+"' where Employee_id='"+user.jt[5].getValueAt(row, 0)+"';";
				        //System.out.println(sql1);
				        PreparedStatement preparedStatement1 = user.conn.prepareStatement(sql1);
				        preparedStatement1.executeUpdate();
				
				        JFrame newFrame = new JFrame();
				        newFrame.setLocation(50,200);
				        JOptionPane.showMessageDialog(newFrame.getContentPane(),
						    "修改成功！", "操作", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				user.conn.close();
			}
			} catch (SQLException e2) {

				e2.printStackTrace();
			} catch (Exception e3) {

				e3.printStackTrace();

			}
		}
		
		
	}
}
