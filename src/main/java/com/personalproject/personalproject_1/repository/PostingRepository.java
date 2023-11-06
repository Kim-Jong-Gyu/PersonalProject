package com.personalproject.personalproject_1.repository;

import com.personalproject.personalproject_1.dto.PostingRequestDto;
import com.personalproject.personalproject_1.dto.PostingResponseDto;
import com.personalproject.personalproject_1.entitiy.Posting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PostingRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Posting save(Posting posting) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT  INTO  posting (postingName, userName, password, postingContent, date) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, posting.getPostingName());
            preparedStatement.setString(4, posting.getPostingContent());
            preparedStatement.setString(2, posting.getUserName());
            preparedStatement.setString(3, posting.getPassword());
            preparedStatement.setString(5, String.valueOf(posting.getDate()));
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        posting.setId(id);
        return posting;
    }
    // password -> "" 값이면 전달받은 값이 없기 때문에 단건조회를 하게 되고 ""이 아닐경우는 update용으로 생각
    public Posting findById(Long id, String password) {
        // DB 조회
        String sql = "SELECT * FROM posting WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                if(password != null){
                    if(!password.equals(resultSet.getString("password"))){
                        return null;
                    }
                }
                Posting posting = new Posting();
                posting.setId(resultSet.getLong("id"));
                posting.setPostingContent(resultSet.getString("postingContent"));
                posting.setPostingName(resultSet.getString("postingName"));
                posting.setUserName(resultSet.getString("userName"));
                posting.setDate(LocalDate.parse(resultSet.getString("date")));
                return posting;
            } else {
                return null;
            }
        }, id);
    }

    public List<PostingResponseDto> findAll() {
        String sql = "SELECT * FROM posting";
        return jdbcTemplate.query(sql, new RowMapper<PostingResponseDto>() {
            @Override
            public PostingResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String postingContent = rs.getString("postingContent");
                String postingName = rs.getString("postingName");
                String userName = rs.getString("userName");
                String date = rs.getString("date");
                return new PostingResponseDto(id,postingName,postingContent,userName,date);
            }
        });
    }

    public void update(Long id, PostingRequestDto requestDto) {
        String sql = "UPDATE posting SET username = ?, postingName = ?, postingContent = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUserName(), requestDto.getPostingName(),requestDto.getPostingContent(), id);
    }

    public void delete(long id) {
        String sql = "DELETE FROM posting WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
