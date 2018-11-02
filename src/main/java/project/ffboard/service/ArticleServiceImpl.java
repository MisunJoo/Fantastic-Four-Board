package project.ffboard.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.ffboard.dao.ArticleDao;
import project.ffboard.dto.Article;
import project.ffboard.dto.ArticleContent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    private int limit = 10; //한페이지에 보여주는 최대 게시글의 갯수
    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    @Transactional
    public int addArticle(Article article, ArticleContent articleContent) {
        //지금 쓰는 글이 답글인경우 groupSeq를 알맞게 조정
        if (article.getGroupId() != null) {
            if(article.getDepthLevel() < 2) article.setDepthLevel(article.getDepthLevel()+1);
            article.setGroupSeq(article.getGroupSeq()+1);
            articleDao.arrangeGroupSeq(article.getGroupId(), article.getGroupSeq());
        }

        //article의 기본정보 삽입.
        articleContent.setArticleId(articleDao.insertArticle(article));

        //article이 원글일 경우 GroupId가 null이므로, 삽입해주는 과정.
        if (article.getGroupId() == null) {
            articleDao.insertGroupId();
        }

        return articleDao.insertArticleContent(articleContent);
    }


    @Override
    @Transactional
    public int updateCount(Long id) {
        return articleDao.increaseHitCount(id);
    }

    @Override
    @Transactional
    public int deleteArticle(Long id) {
        return articleDao.deleteArticle(id);
    }

    @Override
    @Transactional
    public int updateArticle(Article article, ArticleContent articleContent) {
        articleContent.setArticleId(articleDao.updateArticle(article));
        return articleDao.updateArticleContent(articleContent);
    }

    @Override
    @Transactional
    public Article getArticle(Long id) {
        articleDao.increaseHitCount(id);
        return articleDao.getArticle(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleContent getArticleContent(Long id) {
        return articleDao.getArticleContent(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticleList(int categoryId, int start) {
        return articleDao.getArticleList(categoryId,start,limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticleList(int categoryId, int start, String searchType, String searchWord) {
        return articleDao.getArticleList(categoryId,start,limit,searchType,searchWord);
    }

    public int upload(MultipartFile file, Long articleId) {

        //file upload
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dataStr = simpleDateFormat.format(new Date());

        String baseDir = "/home/jycs/tmp";
        String saveDir = baseDir + "/" + dataStr;
        String saveFile = saveDir + "/" + uuidStr;


        File fileObj = new File(saveDir);
        fileObj.mkdirs();

        InputStream in = null;
        OutputStream out = null;

        try{
            in = file.getInputStream();
            out = new FileOutputStream(saveFile);
            byte[] buffer = new byte[1024];
            int readCount = 0;
            while((readCount = in.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if(in != null) {
                try {in.close();} catch (Exception e) {}
            }
            if(out != null) {
                try {out.close();} catch (Exception e) {}
            }//file upload
        }
        return ;
    }
}
