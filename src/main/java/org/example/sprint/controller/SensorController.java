package org.example.sprint.controller;

import org.example.sprint.model.Sensor;
import org.example.sprint.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<Sensor> criar(@RequestBody Sensor sensor) {
        Sensor novoSensor = sensorService.criar(sensor);
        return ResponseEntity.ok(novoSensor);
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> listarTodos() {
        List<Sensor> sensores = sensorService.listarTodos();
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        return sensorService.buscarPorId(id)
                .map(sensor -> ResponseEntity.ok(sensor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizar(@PathVariable Long id, @RequestBody Sensor sensor) {
        return sensorService.atualizar(id, sensor)
                .map(sensorAtualizado -> ResponseEntity.ok(sensorAtualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = sensorService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}