package com.xh.networkclient;

/**
 * 对链路进行ping命令返回的结果，实现Comparable接口，从而让链表ArrayList<LinkResult>可以进行排序
 * @author tinapengbin
 *
 */
public class LinkResult implements Comparable<LinkResult> {
	private String ip;
	private double time;

	public LinkResult(String ip, double time) {
		this.ip = ip;
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@Override
	public int compareTo(LinkResult arg0) {
		if(this.time == 0){
			return 1;
		}
		else if (this.time > arg0.time) {
			return 1;
		} else if (this.time < arg0.time) {
			return -1;
		} else {
			return this.ip.compareTo(arg0.ip);
		}
	}
}
