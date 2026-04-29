package org.example.sprint.service;

import org.example.sprint.model.Sensor;
import org.example.sprint.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor criar(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public List<Sensor> listarTodos() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> buscarPorId(Long id) {
        return sensorRepository.findById(id);
    }

    public Optional<Sensor> atualizar(Long id, Sensor sensorAtualizado) {
        return sensorRepository.findById(id).map(sensor -> {
            sensor.setNome(sensorAtualizado.getNome());
            sensor.setTipo(sensorAtualizado.getTipo());
            sensor.setLocalizacao(sensorAtualizado.getLocalizacao());
            sensor.setStatus(sensorAtualizado.getStatus());

            return sensorRepository.save(sensor);
        });
    }

    public boolean deletar(Long id) {
        if (sensorRepository.existsById(id)) {
            sensorRepository.deleteById(id);
            return true;
        }

        return false;
    }
}