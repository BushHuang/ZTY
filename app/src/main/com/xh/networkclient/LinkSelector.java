package com.xh.networkclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import com.xh.logutils.Log;
import com.xh.xhcore.common.util.XHLog;

public class LinkSelector {
	/**
	 * 对传入的多个ip进行ping命令，并按ping命令时间从小到大排序，若ping不通，则 时间为0，要排在后面
	 * 
	 * @param ips
	 * @return
	 */
	public ArrayList<LinkResult> LinkSort(ArrayList<String> ips) {
		ArrayList<LinkResult> linkResultList = new ArrayList<LinkResult>();
		for (String ip : ips) {
			double time = pingTask(ip);
			LinkResult linkResult = new LinkResult(ip, time);
			linkResultList.add(linkResult);
		}
		Collections.sort(linkResultList);
		return linkResultList;
	}

	private double pingTask(String ip) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip:" + ip);

		double time = 0;
		int timeEqIndex = 0;
		String timeUnit = null;
		String timeEq = "time=";
		String timeStr = null;

		// ping命令发出包
		String command = "ping -c 1 " + ip;

		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = runtime.exec(command);// 调用此接口执行cmd命令
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// 下面是读取控制台输出
		InputStream inputstream = proc.getInputStream();
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

		String line = "";
		try {
			// 从读取的控制台输出中找出ping命令的时间，单位转化成ms
			while ((line = bufferedreader.readLine()) != null) {
				XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " line:" + line);
				timeEqIndex = line.indexOf(timeEq);
				if (timeEqIndex == -1) {
					continue;
				}
				String tmp = line.substring(timeEqIndex + timeEq.length());

				timeStr = tmp.substring(0, tmp.indexOf(" "));

				time = Double.parseDouble(timeStr);

				timeUnit = tmp.substring(tmp.indexOf(" ") + 1);
				if (timeUnit.startsWith("m")) {

				} else {
					time = time * 1000;
				}

				XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " time:" + time);

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 获得cmd命令的执行结果
		try {
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
				Log.e("netCheck", "\nproc.exitValue():" + proc.exitValue());
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}

		return time;
	}
}
