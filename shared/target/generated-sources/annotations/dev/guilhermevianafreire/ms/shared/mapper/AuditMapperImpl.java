package dev.guilhermevianafreire.ms.shared.mapper;

import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangeDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangePropertyType;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangeType;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditHistoryDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditHistoryType;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.javers.core.Changes;
import org.javers.core.commit.CommitMetadata;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-29T19:19:59-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Private Build)"
)
@Component
public class AuditMapperImpl implements AuditMapper {

    @Override
    public AuditHistoryDataDTO snapshotToDto(CdoSnapshot snapshot) {
        if ( snapshot == null ) {
            return null;
        }

        String author = null;
        LocalDateTime dateTime = null;
        Instant dateTimeNoTimeZone = null;
        Map<String, String> properties = null;
        List<String> changedData = null;
        long version = 0L;

        author = snapshotCommitMetadataAuthor( snapshot );
        dateTime = snapshotCommitMetadataCommitDate( snapshot );
        dateTimeNoTimeZone = snapshotCommitMetadataCommitDateInstant( snapshot );
        Map<String, String> properties1 = snapshotCommitMetadataProperties( snapshot );
        Map<String, String> map = properties1;
        if ( map != null ) {
            properties = new HashMap<String, String>( map );
        }
        List<String> list = snapshot.getChanged();
        if ( list != null ) {
            changedData = new ArrayList<String>( list );
        }
        version = snapshot.getVersion();

        BigDecimal id = snapshot.getCommitId().valueAsNumber();
        AuditHistoryType type = AuditHistoryType.valueOf(snapshot.getType().name());
        Map<String, Object> data = snapshot.getState().getPropertyNames().stream().collect(Collectors.toMap(name -> name, name -> snapshot.getPropertyValue(name)));

        AuditHistoryDataDTO auditHistoryDataDTO = new AuditHistoryDataDTO( id, version, type, author, dateTime, dateTimeNoTimeZone, properties, data, changedData );

        return auditHistoryDataDTO;
    }

    @Override
    public List<AuditHistoryDataDTO> snapshotsToDtos(List<CdoSnapshot> snapshots) {
        if ( snapshots == null ) {
            return null;
        }

        List<AuditHistoryDataDTO> list = new ArrayList<AuditHistoryDataDTO>( snapshots.size() );
        for ( CdoSnapshot cdoSnapshot : snapshots ) {
            list.add( snapshotToDto( cdoSnapshot ) );
        }

        return list;
    }

    @Override
    public AuditChangeDataDTO changeToDto(Change change) {
        if ( change == null ) {
            return null;
        }

        BigDecimal id = change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getId().valueAsNumber() : null;
        String author = change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getAuthor() : null;
        LocalDateTime dateTime = change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getCommitDate() : null;
        Instant dateTimeNoTimeZone = change.getCommitMetadata().isPresent() ? change.getCommitMetadata().get().getCommitDateInstant() : null;
        AuditChangeType changeType = AuditChangeType.lookupByChangeTypeName(change.getClass().getSimpleName());
        AuditChangePropertyType changePropertyType = (change instanceof NewObject) ? null : AuditChangePropertyType.valueOf(((ValueChange) change).getChangeType().name());
        String propertyName = (change instanceof NewObject) ? null : ((ValueChange) change).getPropertyName();
        Object oldValue = (change instanceof NewObject) ? null : ((ValueChange) change).getLeft();
        Object newValue = (change instanceof NewObject) ? null : ((ValueChange) change).getRight();

        AuditChangeDataDTO auditChangeDataDTO = new AuditChangeDataDTO( id, author, dateTime, dateTimeNoTimeZone, changeType, propertyName, changePropertyType, oldValue, newValue );

        return auditChangeDataDTO;
    }

    @Override
    public List<AuditChangeDataDTO> changesToDtos(Changes changes) {
        if ( changes == null ) {
            return null;
        }

        List<AuditChangeDataDTO> list = new ArrayList<AuditChangeDataDTO>( changes.size() );
        for ( Change change : changes ) {
            list.add( changeToDto( change ) );
        }

        return list;
    }

    private String snapshotCommitMetadataAuthor(CdoSnapshot cdoSnapshot) {
        if ( cdoSnapshot == null ) {
            return null;
        }
        CommitMetadata commitMetadata = cdoSnapshot.getCommitMetadata();
        if ( commitMetadata == null ) {
            return null;
        }
        String author = commitMetadata.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author;
    }

    private LocalDateTime snapshotCommitMetadataCommitDate(CdoSnapshot cdoSnapshot) {
        if ( cdoSnapshot == null ) {
            return null;
        }
        CommitMetadata commitMetadata = cdoSnapshot.getCommitMetadata();
        if ( commitMetadata == null ) {
            return null;
        }
        LocalDateTime commitDate = commitMetadata.getCommitDate();
        if ( commitDate == null ) {
            return null;
        }
        return commitDate;
    }

    private Instant snapshotCommitMetadataCommitDateInstant(CdoSnapshot cdoSnapshot) {
        if ( cdoSnapshot == null ) {
            return null;
        }
        CommitMetadata commitMetadata = cdoSnapshot.getCommitMetadata();
        if ( commitMetadata == null ) {
            return null;
        }
        Instant commitDateInstant = commitMetadata.getCommitDateInstant();
        if ( commitDateInstant == null ) {
            return null;
        }
        return commitDateInstant;
    }

    private Map<String, String> snapshotCommitMetadataProperties(CdoSnapshot cdoSnapshot) {
        if ( cdoSnapshot == null ) {
            return null;
        }
        CommitMetadata commitMetadata = cdoSnapshot.getCommitMetadata();
        if ( commitMetadata == null ) {
            return null;
        }
        Map<String, String> properties = commitMetadata.getProperties();
        if ( properties == null ) {
            return null;
        }
        return properties;
    }
}
