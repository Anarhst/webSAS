package ru.mirea.intro.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.intro.mapper.RequestMapper;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Request;
import ru.mirea.intro.web.to.Meta;
import ru.mirea.intro.web.to.RequestDto;
import ru.mirea.intro.web.to.Response;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/mirea")
@Api(tags = "Методы для тестирования приложения",description = "Sample text. Ладно.")
public class MireaController {
    private final TestService testService;

    public MireaController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(value = "/post-method")
    @ApiOperation(value = "Пост-метод тестового веб-сервиса <3", notes="Отправление пост-метода")
    public ResponseEntity<Response<String>> postMethod(@RequestBody RequestDto requestDto, @RequestParam Optional<String> optionalStringValue) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto); //Преобразуем запрос
            String testServiceResponse = testService.testServicePostMethod(request); //Реализация бизнес-логики
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/get-method")
    @ApiOperation(value = "Гет-метод тестового веб-сервиса", notes="Запрос на получение данных с сервера")
    public ResponseEntity<Response<RequestDto>> getMethod(@RequestParam(name = "Идентификатор запроса") Long id) {
        try {
            Request request = testService.testServiceGetMethod(id);
            RequestDto requestDto = RequestMapper.REQUEST_MAPPER.requestToRequestDto(request);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), requestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/put-method")
    @ApiOperation(value = "Пут-метод тестового веб-сервиса",notes = "Запрос на обновление данных")
    public ResponseEntity<Response<String>> putMethod(@RequestBody RequestDto requestDto) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto);
            String testServiceResponse = testService.testServicePutMethod(request);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete-method")
    @ApiOperation(value = "Делит-метод тестового веб-сервиса",notes = "Запрос на удаление данных")
    public  ResponseEntity<Response<String>> deleteMethod(@RequestParam(name = "Идентификатор запроса") Long id) {
        try {
            String testServiceResponse = testService.testServiceDeleteMethod(id);
            return new ResponseEntity<>(new Response<>(new Meta(0,"All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }

    }
}
