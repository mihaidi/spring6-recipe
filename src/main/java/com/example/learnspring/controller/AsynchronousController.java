package com.example.learnspring.controller;

import com.example.learnspring.model.Reservation;
import jakarta.validation.Valid;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.Callable;

@Controller
@SessionAttributes("reservation")
public class AsynchronousController {

    private final TaskExecutor taskExecutor;

    public AsynchronousController(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }


    @PostMapping
    public Callable<String> submitForm(@ModelAttribute("reservation") @Valid
                                       Reservation reservation,
                                       BindingResult bindingResult,
                                       SessionStatus sessionStatus) {

        return () -> {

            if (bindingResult.hasErrors()) {
                return "errorView";
            } else {

                Thread.sleep(1000);
                sessionStatus.setComplete();

                return "successView";
            }

        };
    }

    @GetMapping
    public ResponseBodyEmitter getRestMember() {

        var emitter = new ResponseBodyEmitter();

        List<String> responses = List.of("first", "second", "third");

        taskExecutor.execute(() -> {

            responses.forEach(r -> {

                try {
                    emitter.send(r);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            emitter.complete();

        });
        return emitter;
    }

    @GetMapping("/sse")
    public ResponseBodyEmitter getRestMemberWithSse() {

        var emitter = new SseEmitter();

        List<String> responses = List.of("first", "second", "third");

        taskExecutor.execute(() -> {

            responses.forEach(r -> {

                try {
                    emitter.send(SseEmitter.event().id("1").name("name").data(r).build());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            emitter.complete();

        });
        return emitter;
    }


}
