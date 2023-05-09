package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface SuggestService {

    // 저장
    public void register(SuggestDTO suggestDTO, Long memberId);
    // 목록
    public List<SuggestDTO> getSuggestList(Pageable pageable);
    // 상세 보기
    public SuggestDTO getSuggest(Long suggestId);

    // 현재 시퀀스 가져오기
    public Suggest getCurrentSequence();

    // 수정


    default SuggestDTO toSuggestDTO(Suggest suggest) {
        return SuggestDTO.builder()
                .id(suggest.getId())
                .boardTitle(suggest.getBoardTitle())
                .boardContent(suggest.getBoardContent())
                .boardType(suggest.getBoardType())
                .memberDTO(toMemberDTO(suggest.getMember()))
                .fileDTOS(FileToDTO(suggest.getSuggestFiles()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .memberId(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    default List<FileDTO> FileToDTO(List<SuggestFile> suggestFiles) {
        List<FileDTO> suggestFileList = new ArrayList<>();
        suggestFiles.forEach(
                suggestFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(suggestFile.getId())
                            .fileOriginalName(suggestFile.getFileOriginalName())
                            .fileUuid(suggestFile.getFileUuid())
                            .filePath(suggestFile.getFilePath())
                            .build();
                    suggestFileList.add(fileDTO);
                }
        );
        return suggestFileList;
    }

    default Suggest toSuggestEntity(SuggestDTO suggestDTO) {
        return Suggest.builder()
                .id(suggestDTO.getId())
                .boardTitle(suggestDTO.getBoardTitle())
                .boardContent(suggestDTO.getBoardContent())
                .boardType(suggestDTO.getBoardType())
                .member(toMemberEntity(suggestDTO.getMemberDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getMemberId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .build();
    }

    default SuggestFile toSuggestFileEntity(FileDTO fileDTO){
        return SuggestFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .suggest(fileDTO.getSuggest())
                .build();
    }

    default List<SuggestFile> toSuggestFileListEntity(List<FileDTO> fileDTOS){
        List<SuggestFile> suggestFiles = new ArrayList<>();

        fileDTOS.forEach(
                fileDTO -> {
                    SuggestFile suggestFile = SuggestFile.builder()
                            .id(fileDTO.getId())
                            .fileOriginalName(fileDTO.getFileOriginalName())
                            .fileUuid(fileDTO.getFileUuid())
                            .filePath(fileDTO.getFilePath())
                            .build();
                    suggestFiles.add(suggestFile);
                }
        );
        return suggestFiles;
    }

}
