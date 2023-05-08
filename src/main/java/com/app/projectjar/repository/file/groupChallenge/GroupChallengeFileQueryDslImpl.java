package com.app.projectjar.repository.file.groupChallenge;

import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;

@RequiredArgsConstructor
public class GroupChallengeFileQueryDslImpl implements GroupChallengeFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Optional<GroupChallengeFile> findTop1ByGroupChallengeFileId_QueryDsl(Long groupChallengeId) {
        GroupChallengeFile foundFile = query.select(groupChallengeFile)
                .from(groupChallengeFile)
                .where(groupChallengeFile.groupChallenge.id.eq(groupChallengeId))
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(foundFile);
    }
}
