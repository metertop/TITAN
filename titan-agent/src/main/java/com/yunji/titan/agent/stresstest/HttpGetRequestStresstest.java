/*
 * Copyright (C) 2015-2020 yunjiweidian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.yunji.titan.agent.stresstest;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yunji.titan.agent.adapter.BaseStresstestAdapter;
import com.yunji.titan.agent.bean.bo.OutParamBO;
import com.yunji.titan.agent.config.HttpConnectionManager;
import com.yunji.titan.agent.interpreter.param.AbstractExpression;
import com.yunji.titan.agent.interpreter.param.ParamContext;
import com.yunji.titan.agent.utils.ParamUtils;
import com.yunji.titan.utils.ContentType;

/**
 * 执行GET请求类型压测
 * 
 * @author gaoxianglong
 */
@Service("httpGetRequestStresstest")
public class HttpGetRequestStresstest extends BaseStresstestAdapter {
	@Resource
	public HttpConnectionManager httpConnectionManager;
	@Resource(name = "headerExpression")
	private AbstractExpression headerExpression;
	@Resource(name = "paramExpression")
	private AbstractExpression paramExpression;
	private Logger log = LoggerFactory.getLogger(HttpGetRequestStresstest.class);


	@Override
	public OutParamBO runStresstest(String url, String outParam, String param, ContentType contentType,
									String charset) {
		return runGetStresstest(url, outParam, param, contentType, charset);
	}

	@Override
	public OutParamBO runGetStresstest(String url, String outParam, String param, ContentType contentType,
			String charset) {
		OutParamBO outParamBO = new OutParamBO();
		if (!StringUtils.isEmpty(url)) {
			HttpEntity entity = null;
			CloseableHttpClient httpClient = null;
			CloseableHttpResponse httpResponse = null;
			/* 解析参数 */
			ParamContext context = new ParamContext();
			context.setParams(param);
			param = paramExpression.get(context);
			String header = headerExpression.get(context);
			log.info("接口请求header->{}, param->{}", header, param);

			/* 将上一个接口的出参作为下一个接口的入参拼接 */
			if (!StringUtils.isEmpty(outParam)) {
				String value = ParamUtils.jsonToUrl(param, outParam);
				/* 动参合并操作 */
				param = StringUtils.isEmpty(value) ? param : value;
			}
			log.info("链路测试，将上个接口的返回的参数作为下个接口的入参拼接后，请求为->{}", param);

			try {
				httpClient = httpConnectionManager.getHttpClient();
				if (null != httpClient) {
					HttpGet request = new HttpGet(
							com.yunji.titan.utils.UrlEncoder.encode(null != param ? url + param : url));
					log.info("get请求串为->{}", (null != param ? url + param : url));

					/* 设置请求头 */
					ParamUtils.setHeader(request, header, contentType, charset);
					httpResponse = httpClient.execute(request);
					entity = httpResponse.getEntity();


					/* 获取压测执行结果 */
					outParamBO = super.getResult(httpResponse, entity);
					log.info("get请求返回结果为{}：", outParamBO);
				}
			} catch (Exception e) {
				// ...
			} finally {
				try {
					if (null != entity) {
						entity.getContent().close();
					}
					if (null != httpResponse) {
						httpResponse.close();
					}
				} catch (IOException e) {
					log.error("error", e);
				}
			}
		}
		return outParamBO;
	}
}