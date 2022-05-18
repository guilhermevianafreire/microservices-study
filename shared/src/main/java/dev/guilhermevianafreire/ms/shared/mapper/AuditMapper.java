package dev.guilhermevianafreire.ms.shared.mapper;

import dev.guilhermevianafreire.ms.shared.dto.audit.AuditDataDTO;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(imports = {Collectors.class, Function.class, BigDecimal.class})
public interface AuditMapper {

    @Mapping(target = "id", expression = "java(snapshot.getCommitId().valueAsNumber())")
    @Mapping(target = "type", expression = "java(AuditDataType.valueOf(snapshot.getType().name()))")
    @Mapping(target = "author", source = "commitMetadata.author")
    @Mapping(target = "dateTime", source = "commitMetadata.commitDate")
    @Mapping(target = "dateTimeNoTimeZone", source = "commitMetadata.commitDateInstant")
    @Mapping(target = "properties", source = "commitMetadata.properties")
    @Mapping(target = "data", expression = "java(snapshot.getState().getPropertyNames().stream().collect(Collectors.toMap(name -> name, name -> CdoSnapshot::getPropertyValue)))")
    @Mapping(target = "changedData", source = "changed")
    AuditDataDTO snapshotToDto(CdoSnapshot snapshot);

    List<AuditDataDTO> snapshotsToDtos(List<CdoSnapshot> snapshots);

}
