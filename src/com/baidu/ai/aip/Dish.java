package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.dbconnect.DBHelper;
import com.baidu.ai.dishinform.Dishinformation;
import com.alibaba.fastjson.JSON;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.alibaba.fastjson.JSONObject;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

/**
 * 菜品识别
 */
public class Dish {
	static String path = null;

	public static String getFilePath() {
		return path;
	}

	public static void setPath(String path1) {
		path = path1;
	}

	public static void filechoose() {
		JFrame win = new JFrame("文件选择");
		if (UIManager.getLookAndFeel().isSupportedLookAndFeel()) {
			final String platform = UIManager.getSystemLookAndFeelClassName();
			if (!UIManager.getLookAndFeel().getName().equals(platform)) {
				try {
					UIManager.setLookAndFeel(platform);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		int returnval = 0;
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView(); // 注意了，这里重要的一句
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("请选择要上传的文件...");
		fileChooser.setApproveButtonText("确定");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		returnval = fileChooser.showOpenDialog(win);
		if (JFileChooser.APPROVE_OPTION == returnval) {
			path = fileChooser.getSelectedFile().getPath();
		}
	}
	
	public static String dish() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
		try {
			// Dish.setPath(path);
			String filePath = path;
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam + "&top_num=" + 2 + "&baike_num=" + 1;

			String accessToken = "24.19d10676033c0804338434a76e016a1e.2592000.1573733242.282335-15847527";

			String result = HttpUtil.post(url, accessToken, param);
			JSONObject jsonobject = JSON.parseObject(result);
			System.out.print("result_num:" + jsonobject.getIntValue("result_num") + "   ");
			System.out.print("log_id:" + jsonobject.getString("log_id") + " ");
			System.out.print("result: ");

			String ff = jsonobject.getJSONArray("result").getString(0);
			System.out.print(ff + "  ");
			JSONObject jsonObject1 = JSON.parseObject(ff);
			new Dishinformation();
			String ff1 = jsonobject.getJSONArray("result").getString(1);
			JSONObject jsonObject2 = JSON.parseObject(ff1);
			JSONObject jsonObject3 = JSON.parseObject(jsonObject1.getString("baike_info"));
			System.out.print(jsonObject1.getFloatValue("probability") + "  " + jsonObject1.getFloatValue("calorie")
					+ " " + jsonObject1.getString("name") + " " + jsonObject2.getString("name") + " "
					+ jsonObject3.getString("description"));
			Dishinformation.Dishinform(jsonObject1.getString("name"), jsonObject1.getFloatValue("calorie"),
					jsonObject1.getFloatValue("probability"), jsonObject2.getString("name"),
					jsonObject3.getString("description"));
			Connection con = DBHelper.getConnection();
			String sql = "INSERT dish (name,calorie,probability,description)VALUE('" + jsonObject1.getString("name")
					+ "','" + jsonObject1.getFloatValue("calorie") + "','" + jsonObject1.getFloatValue("probability")
					+ "'," + "'" + jsonObject3.getString("description") + "')";
			Statement st = null;
			try {
				st = con.createStatement();
				st.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}