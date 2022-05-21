package dev.guilhermevianafreire.ms.shared.mapper;

import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangeDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangePropertyType;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditHistoryDataDTO;
import org.javers.core.Changes;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = {Collectors.class, BigDecimal.class, ValueChange.class, NewObject.class})
public interface AuditMapper {

    @Mapping(target = "id", expression = "java(snapshot.getCommitId().valueAsNumber())")
    @Mapping(target = "type", expression = "java(dev.guilhermevianafreire.ms.shared.dto.audit.AuditHistoryType.valueOf(snapshot.getType().name()))")
    @Mapping(target = "author", source = "commitMetadata.author")
    @Mapping(target = "dateTime", source = "commitMetadata.commitDate")
    @Mapping(target = "dateTimeNoTimeZone", source = "commitMetadata.commitDateInstant")
    @Mapping(target = "properties", source = "commitMetadata.properties")
    @Mapping(target = "data", expression = "java(snapshot.getState().getPropertyNames().stream().collect(Collectors.toMap(name -> name, name -> snapshot.getPropertyValue(name))))")
    @Mapping(target = "changedData", source = "changed")
    AuditHistoryDataDTO snapshotToDto(CdoSnapshot snapshot);

    List<AuditHistoryDataDTO> snapshotsToDtos(List<CdoSnapshot> snapshots);

    @Mapping(target = "id", expression = "java(change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getId().valueAsNumber() : null)")
    @Mapping(target = "author", expression = "java(change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getAuthor() : null)")
    @Mapping(target = "dateTime", expression = "java(change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getCommitDate() : null)")
    @Mapping(target = "dateTimeNoTimeZone", expression = "java(change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getCommitDateInstant() : null)")
    @Mapping(target = "changeType", expression = "java(AuditChangeType.lookupByChangeTypeName(change.getClass().getSimpleName()))")
    @Mapping(target = "changePropertyType", expression = "java((change instanceof NewObject) ? null : AuditChangePropertyType.valueOf(((ValueChange) change).getChangeType().name()))")
    @Mapping(target = "propertyName", expression = "java((change instanceof NewObject) ? null : ((ValueChange) change).getPropertyName())")
    @Mapping(target = "oldValue", expression = "java((change instanceof NewObject) ? null : ((ValueChange) change).getLeft())")
    @Mapping(target = "newValue", expression = "java((change instanceof NewObject) ? null : ((ValueChange) change).getRight())")
    AuditChangeDataDTO changeToDto(Change change);

    List<AuditChangeDataDTO> changesToDtos(Changes changes);

}
