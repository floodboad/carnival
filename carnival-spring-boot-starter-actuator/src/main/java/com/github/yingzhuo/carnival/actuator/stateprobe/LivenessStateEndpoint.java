/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.actuator.stateprobe;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Map;

/**
 * @author 应卓
 * @since 1.6.32
 */
@RestControllerEndpoint(id = "liveness")
public class LivenessStateEndpoint extends AbstractStateEndpoint {

    @GetMapping("/{state}")
    public Map<String, Object> excute(@PathVariable("state") String state) {
        final LivenessState s;

        try {
            if (enable(state)) {
                s = LivenessState.CORRECT;
            } else {
                s = LivenessState.BROKEN;
            }
        } catch (IllegalArgumentException exception) {
            return Collections.singletonMap("error", "unkown state '" + state + "'");
        }

        AvailabilityChangeEvent.publish(applicationContext, s);
        return Collections.singletonMap("current", s);
    }

}
