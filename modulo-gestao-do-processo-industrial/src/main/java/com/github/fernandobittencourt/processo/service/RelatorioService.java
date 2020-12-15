package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.Relatorio;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.processo.domain.vo.RelatorioVo;
import com.github.fernandobittencourt.processo.repository.ProcessoRepository;
import com.github.fernandobittencourt.processo.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private INormaService normaService;

    @Autowired
    private IConsultoriaService consultoriaService;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    public Relatorio incluirRelatorio(RelatorioVo relatorioVo){
        Long processoId = relatorioVo.getProcesso();
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(RuntimeException::new);
        Relatorio relatorio = new Relatorio();
        relatorio.setProcesso(processo);
        relatorio.setLink(relatorioVo.getLink());
        relatorio = relatorioRepository.save(relatorio);
        return relatorio;
    }

}
