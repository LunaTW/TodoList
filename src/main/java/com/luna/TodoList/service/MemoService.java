package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.exception.IncorrectInformationException;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.model.Users;
import com.luna.TodoList.repository.MemoRepository;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public MemoService(MemoRepository memoRepository, UserRepository userRepository) {
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
    }

    public Users addMemo(Long userId, MemoRequestDto memoRequestDto)  throws IncorrectInformationException {

        Memo memo = Memo.builder().message(memoRequestDto.getMessage())
                .tag(memoRequestDto.getTag())
                .complete(memoRequestDto.getComplete())
                .publicity(memoRequestDto.getPublicity())
                .localDate_created(LocalDate.now())
                .localDate_modified(LocalDate.now())
                .build();
        memoRepository.save(memo);
        Users usersToUpdate = userRepository.getOne(userId);
        List<Memo> memos = usersToUpdate.getMemoList();
        memos.add(memo);
        usersToUpdate.setMemoList(memos);
        return usersToUpdate;
    }
//
//    public Memo getMemoById(Long memoId, Long loginUserId) throws NotFoundException {
//        Memo memoToCheck = memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException("Memo not exist"));
//
//        if (memoToCheck.getUserId().equals(loginUserId) || userRepository.findAdminByUserId(loginUserId)){
//            return memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException("Memo not exist"));
//        } else {
//            throw new IncorrectInformationException("You Do Not Have Access");
//        }
//    }
//
//    public List<Memo> getMemosByTag(String tag, Long loginUserId){
//        if (userRepository.findAdminByUserId(loginUserId)){
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getTag().equals(tag))
//                    .collect(Collectors.toList());
//        } else {
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getTag().equals(tag) && (memo.getPublicity().equals(TRUE) || memo.getUserId().equals(loginUserId)))
//                    .collect(Collectors.toList());
//        }
//    }
//
//    public List<Memo> getMemosByCompleted(Boolean completed, Long loginUserId){
//        if (userRepository.findAdminByUserId(loginUserId)){
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getComplete().equals(completed))
//                    .collect(Collectors.toList());
//        } else {
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getComplete().equals(completed) && (memo.getPublicity().equals(TRUE) || memo.getUserId().equals(loginUserId)))
//                    .collect(Collectors.toList());
//        }
//    }
//
//    public List<Memo> getMemosByKeyword(String keyword, Long loginUserId){
//        if (userRepository.findAdminByUserId(loginUserId)){
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getMessage().contains(keyword))
//                    .collect(Collectors.toList());
//        } else {
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getMessage().contains(keyword) && (memo.getPublicity().equals(TRUE) || memo.getUserId().equals(loginUserId)))
//                    .collect(Collectors.toList());
//        }
//    }
//
//    public List<Memo> getAllMemos(Long loginId) throws IncorrectInformationException {
//        if (userRepository.findAdminByUserId(loginId)){
//            return memoRepository.findAll();
//        }else {
//            return memoRepository.findAll().stream().filter(memo -> memo.getPublicity().equals(true) || memo.getUserId().equals(loginId)).collect(Collectors.toList());
//        }
//    }
//
//    public List<Memo> getMemoByUserId(Long id, Long loginUserId) {
//        if (userRepository.findAdminByUserId(loginUserId) || id.equals(loginUserId)) {
//            return memoRepository.findAll()
//                    .stream()
//                    .filter(memo -> memo.getUserId().equals(id))
//                    .collect(Collectors.toList());
//        } else {
//            throw new IncorrectInformationException("You Do Not Have Access");
//        }
//    }
//
//    public String deleteMemosById(Long memoId, Long loginUserId) throws IncorrectInformationException {
//        if (memoRepository.findUserIdById(memoId)==loginUserId || userRepository.findAdminByUserId(loginUserId)){
//            memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException("Memo not exist"));
//            memoRepository.deleteById(memoId);
//            return "SUCCESS";
//        } else {
//            throw new IncorrectInformationException("You Do Not Have Access");
//        }
//    }
//
//    @Transactional
//    public Long deleteMemosByUserId(Long userId, Long loginUserId) throws NotFoundException {
//        if (userId.equals(loginUserId) || userRepository.findAdminByUserId(loginUserId)){
//            userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not exist"));
//            return memoRepository.deleteByUserId(userId);
//        } else {
//            throw new IncorrectInformationException("You Do Not Have Access");
//        }
//    }
//
//    public Memo updateMemo(Long id, Long loginUserId, MemoRequestDto memoRequestDto) throws NotFoundException {
//        if (memoRepository.findUserIdById(id)==loginUserId){
//            memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
//            Memo memoToUpdate = memoRepository.getOne(id);
//            memoToUpdate.setMessage(memoRequestDto.getMessage());
//            memoToUpdate.setTag(memoRequestDto.getTag());
//            memoToUpdate.setComplete(memoRequestDto.getComplete());
//            memoToUpdate.setLocalDate_modified(LocalDate.now());
//            memoToUpdate.setPublicity(memoRequestDto.getPublicity());
//            memoRepository.save(memoToUpdate);
//            return memoToUpdate;
//        }else{
//            throw new NoAccessException("You do not have access");
//        }
//    }
//
//    public Memo shareMemo(Long userId, Memo memo) {
//        Memo newMemo = Memo.builder().message(memo.getMessage())
//                .tag(memo.getTag())
//                .complete(memo.getComplete())
//                .publicity(memo.getPublicity())
//                .localDate_created(LocalDate.now())
//                .localDate_modified(LocalDate.now())
//                .userId(userId)
//                .build();
//        memoRepository.save(newMemo);
//        return newMemo;
//    }
}