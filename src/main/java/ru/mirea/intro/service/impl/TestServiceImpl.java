package ru.mirea.intro.service.impl;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.mirea.intro.dao.RequestDAO;
import ru.mirea.intro.dao.repository.RequestRepository;
import ru.mirea.intro.exception.NoSuchRequest;
import ru.mirea.intro.mapper.RequestMapper;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Request;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request testServiceGetMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(id);
        if (requestDAO.isPresent()) {
            Request request = RequestMapper.REQUEST_MAPPER.requestDAOToRequest(requestDAO.get());
            request.SortList();
            return request;
        }
        throw new NoSuchRequest();
    }

    @Override
    public String testServicePostMethod(Request request) {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));
        requestRepository.save(requestDAO);
        request.SortList();
        return request.toString();
    }

    @Override
    public String testServicePutMethod(Request request) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(request.getId());
        if (requestDAO.isPresent()) {
            RequestDAO requestPutDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
            requestPutDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestPutDAO));
            requestRepository.save(requestPutDAO);
            request.SortList();
            return request.toString();
        }
        throw new NoSuchRequest();

    }

    @Override
    public String testServiceDeleteMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(id);
        if (requestDAO.isPresent()) {
            requestRepository.delete(requestDAO.get());
            return "Successfully deleted row!";
        }
        throw new NoSuchRequest();
    }
}