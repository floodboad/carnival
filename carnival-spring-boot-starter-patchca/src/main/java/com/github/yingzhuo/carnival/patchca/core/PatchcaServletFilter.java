/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.patchca.core;

import lombok.extern.slf4j.Slf4j;
import org.patchca.service.CaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 应卓
 */
@Slf4j
public class PatchcaServletFilter extends OncePerRequestFilter {

    private final CaptchaService captchaService;
    private final String patchcaSessionAttributeName;

    public PatchcaServletFilter(CaptchaService captchaService, String patchcaSessionAttributeName) {
        this.captchaService = captchaService;
        this.patchcaSessionAttributeName = patchcaSessionAttributeName;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        response.setContentType("image/png");
        response.setHeader("cache", "no-cache");

        HttpSession session = request.getSession(true);
        OutputStream outputStream = response.getOutputStream();
        String patchca = EncoderHelper.write(captchaService, "png", outputStream);
        session.setAttribute(patchcaSessionAttributeName, patchca);
        log.info("SessionAttribute: {}={}", patchcaSessionAttributeName, patchca);

        outputStream.flush();
        outputStream.close();
    }

}
