package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.exception.IncorrectInformationException;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public MemoService(MemoRepository memoRepository, UserRepository userRepository) {
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
    }

    public Memo addMemo(Long userId, MemoRequestDto memoRequestDto)  throws IncorrectInformationException {
        if (!userId.equals(memoRequestDto.getUserId())){
            throw new IncorrectInformationException("You Do Not Have Access");
        }
        Memo memo = Memo.builder().message(memoRequestDto.getMessage())
                .tag(memoRequestDto.getTag())
                .complete(memoRequestDto.getComplete())
                .publicity(memoRequestDto.getPublicity())
                .localDate_created(LocalDate.now())
                .localDate_modified(LocalDate.now())
                .userId(memoRequestDto.getUserId())
                .build();
        memoRepository.save(memo);
        return memo;
    }

    public Memo getMemoById(Long id, Long loginUserId) throws NotFoundException {
        if (id.equals(loginUserId) || userRepository.findAdminByUserId(loginUserId)){
            return memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
        } else {
            throw new IncorrectInformationException("You Do Not Have Access");
        }
    }

    public List<Memo> getMemosByTag(String tag, Long loginUserId){
        if (userRepository.findAdminByUserId(loginUserId)){
            List<Memo> allMemosByTag = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getTag().equals(tag))
                    .collect(Collectors.toList());
            return allMemosByTag;
        } else {
            List<Memo> allMemosByTag = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getTag().equals(tag) && memo.getPublicity().equals(TRUE))
                    .collect(Collectors.toList());
            return allMemosByTag;
        }
    }

    public List<Memo> getMemosByCompleted(Boolean completed, Long loginUserId){
        if (userRepository.findAdminByUserId(loginUserId)){
            List<Memo> allMemosByCompleted = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getComplete().equals(completed))
                    .collect(Collectors.toList());
            return allMemosByCompleted;
        } else {
            List<Memo> allMemosByCompleted = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getComplete().equals(completed) && memo.getPublicity().equals(TRUE))
                    .collect(Collectors.toList());
            return allMemosByCompleted;
        }
    }

    public List<Memo> getMemosByKeyword(String keyword, Long loginUserId){
        if (userRepository.findAdminByUserId(loginUserId)){
            List<Memo> allMemosByKeyword = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getMessage().contains(keyword))
                    .collect(Collectors.toList());
            return allMemosByKeyword;
        } else {
            List<Memo> allMemosByKeyword = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getMessage().contains(keyword) && memo.getPublicity().equals(TRUE))
                    .collect(Collectors.toList());
            return allMemosByKeyword;
        }
    }

    public List<Memo> getAllMemos(Long loginId) throws IncorrectInformationException {
        if (userRepository.findAdminByUserId(loginId)){
            return memoRepository.findAll();
        }else {
            throw new IncorrectInformationException("You Do Not Have Access");
        }
    }

    public List<Memo> getMemoByUserId(Long id, Long loginUserId) {
        if (userRepository.findAdminByUserId(loginUserId) || id.equals(loginUserId)) {
            List<Memo> allMemosByUserId = memoRepository.findAll()
                    .stream()
                    .filter(memo -> memo.getUserId().equals(id))
                    .collect(Collectors.toList());
            return allMemosByUserId;
        } else {
            throw new IncorrectInformationException("You Do Not Have Access");
        }
    }

    public String deleteMemosById(Long memoId, Long loginUserId) throws IncorrectInformationException {
        if (memoRepository.findUserIdById(memoId)==loginUserId || userRepository.findAdminByUserId(loginUserId)){
            memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException("Memo not exist"));
            memoRepository.deleteById(memoId);
            return "SUCCESS";
        } else {
            throw new IncorrectInformationException("You Do Not Have Access");
        }
    }

    public String deleteMemosByUserId(Long userId, Long loginUserId) throws NotFoundException {
        if (userId.equals(loginUserId) || userRepository.findAdminByUserId(loginUserId)){
            userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not exist"));
            memoRepository.deleteByUserId(userId);
            return "SUCCESS";
        } else {
            throw new IncorrectInformationException("You Do Not Have Access");
        }
    }

    // cannot change user for now: for design
    public Memo updateMemo(Long id, MemoRequestDto memoRequestDto) throws NotFoundException {
        memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
        Memo memoToUpdate = memoRepository.getOne(id);
        memoToUpdate.setMessage(memoRequestDto.getMessage());
        memoToUpdate.setTag(memoRequestDto.getTag());
        memoToUpdate.setComplete(memoRequestDto.getComplete());
        memoToUpdate.setLocalDate_modified(LocalDate.now());
        memoToUpdate.setPublicity(memoRequestDto.getPublicity());
        memoRepository.save(memoToUpdate);
        return memoToUpdate;
    }
}
